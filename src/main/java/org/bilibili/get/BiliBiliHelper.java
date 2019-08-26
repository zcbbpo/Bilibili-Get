package org.bilibili.get;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.bilibili.get.constants.ApiConsts;
import org.bilibili.get.constants.AppKeyConsts;
import org.bilibili.get.dto.*;
import org.bytedeco.javacpp.Loader;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

/**
 * @author Jim
 */
public class BiliBiliHelper {
    private static Logger logger = LoggerFactory.getLogger(BiliBiliHelper.class);
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";
    private CloseableHttpClient httpClient;

    private Path videoStoragePath;

    private String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);

    public BiliBiliHelper(Path videoStoragePath) throws Exception{
        Objects.requireNonNull(videoStoragePath);
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        int timeout = 180 * 1000;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();
        httpClient =  HttpClientBuilder.create()
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .setSSLSocketFactory(new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true).build()))
                .setDefaultRequestConfig(config)
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(100)
                .build();
        this.videoStoragePath = videoStoragePath;
        if (Files.notExists(videoStoragePath)) {
            Files.createDirectories(videoStoragePath);
        }
    }

    /**
     * 下载某个up主的所有视频
     * @param userId up主的id eg: [https://space.bilibili.com/1315101] 的uid就是1315101
     * @throws Exception ex
     */
    public void downloadForUid(String userId) throws Exception{

        int page = 1;
        List<CommentDTO> list = new ArrayList<>(300);
        while (true) {
            HttpGet httpGet = new HttpGet(String.format("%s?mid=%s&pagesize=100&tid=0&page=%d&keyword=&order=pubdate", ApiConsts.ALL_VIDEO_LIST_API_URL, userId, page));
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
            String result = EntityUtils.toString(closeableHttpResponse.getEntity());
            closeableHttpResponse.close();
            ResponseDTO responseDTO;
            try {
                responseDTO = JSON.parseObject(result, ResponseDTO.class);
                list.addAll(responseDTO.getData().getCommentList());
            }catch (Exception e) {
                logger.error(result);
                throw e;
            }

            page++;

            if (page > responseDTO.getData().getPages()) {
                break;
            }
            //太频繁会被封ip
            TimeUnit.MILLISECONDS.sleep(500);
        }
        logger.info("Video list size: {}", list.size());

        list.sort(Comparator.comparingLong(c -> c.getCreated().getTime()));

        list.parallelStream()
                .forEach(avComment -> {
                    try {
                        logger.info("Start {} {} [av{}]", DateFormatUtils.format(avComment.getCreated(), "yyyy-MM-dd"), avComment.getTitle(), avComment.getAid());
                        downloadForAid(avComment.getAid(), avComment.getTitle());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public void downloadForAid(String aid) throws Exception {
        downloadForAid(aid, null);
    }

    private void downloadForAid(String aid, String title) throws Exception {
        String refineAid = StringUtils.replace(aid, "av", "");

        if (StringUtils.isBlank(title)) {
            CommentDTO commentDTO = getVideoInfoByAid(refineAid);
            title = commentDTO.getTitle();
        }

        List<PageListDTO> cidList = getCidByAid(refineAid);

        for (PageListDTO pageCid : cidList) {
            String cid = String.valueOf(pageCid.getCid());
            PlayUrlDTO playUrlDTO = getPlayUrl(refineAid, cid);

            Path videoPath;
            if (Objects.nonNull(playUrlDTO.getData().getDash())) {
                videoPath = downloadVideoByDash(playUrlDTO.getData(), refineAid, cid);
            } else {
                playUrlDTO.setData(getDurl(String.valueOf(pageCid.getCid())));
                videoPath = downloadVideoByDurl(playUrlDTO.getData(), refineAid, cid);
            }
            String fileTitle;
            if (cidList.size() == 1) {
                fileTitle = title;
            } else {
                fileTitle = pageCid.getPageName() + "_" + pageCid.getPage() + "P";
            }

            String fileName = refineFileName(fileTitle);
            Files.move(videoPath, videoPath.resolveSibling(fileName + ".mp4"), StandardCopyOption.REPLACE_EXISTING);

            Path bulletPath = Paths.get(videoPath.getParent().toString(), fileName + ".json");
            Files.deleteIfExists(bulletPath);
            Files.createFile(bulletPath);
            String bulletString = getBulletComment(cid);

            OutputStream os = Files.newOutputStream(bulletPath);

            JSONObject xmlJSONObj = XML.toJSONObject(bulletString).optJSONObject("i");
            BulletCommentDTO bulletCommentDTO = JSON.parseObject(xmlJSONObj.toString(), BulletCommentDTO.class);
            IOUtils.write(new JSONObject(bulletCommentDTO).toString(1), os, UTF_8);
            IOUtils.closeQuietly(os);
        }
    }

    private CommentDTO getVideoInfoByAid(String aid) throws Exception{
        String url = String.format("%s?type=jsonp&appkey=8e9fc618fbd41e28&id=%s",
                ApiConsts.VIDEO_INFO_V2_API_URL, aid);
        return dataGet(url, new TypeReference<CommentDTO>(){});
    }

    private List<PageListDTO> getCidByAid(String aid) throws Exception {
        HttpGet httpGet = new HttpGet(String.format("%s?aid=%s", ApiConsts.VIDEO_CID_GET_API_URL, aid));
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        if (closeableHttpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException(closeableHttpResponse.getStatusLine().getReasonPhrase());
        }
        String result = EntityUtils.toString(closeableHttpResponse.getEntity());
        closeableHttpResponse.close();
        List<PageListDTO> list = JSON.parseObject(result, new TypeReference<List<PageListDTO>>(){});
        list.sort(Comparator.comparingInt(PageListDTO::getPage));
        return list;
    }

    private PlayUrlDTO getPlayUrl(String aid, String cid) throws Exception{
        String url = String.format("%s?avid=%s&cid=%s&qn=16&type=&otype=json&fnver=0&fnval=16",
                ApiConsts.VIDEO_X_PLAY_URL, aid, cid);
        return dataGet(url, new TypeReference<PlayUrlDTO>(){});
    }

    private PlayUrlDTO.DataDTO getDurl(String cid) throws Exception{
        String params = String.format("appkey=%s&cid=%s&otype=json&qn=80&quality=80&type=", AppKeyConsts.APP, cid);
        String checkSum = DigestUtils.md5Hex(params + AppKeyConsts.SECRET);
        String url = String.format("%s?%s&sign=%s",
                ApiConsts.VIDEO_V2_PLAY_URL, params, checkSum);
        return dataGet(url, new TypeReference<PlayUrlDTO.DataDTO>(){});
    }

    private <T> T dataGet(String url, TypeReference<T> typeReference) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        if (closeableHttpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException(closeableHttpResponse.getStatusLine().getReasonPhrase());
        }
        String result = EntityUtils.toString(closeableHttpResponse.getEntity());
        closeableHttpResponse.close();
        return JSON.parseObject(result, typeReference);
    }

    private Path downloadVideoByDash(PlayUrlDTO.DataDTO data, String aid, String cid) throws Exception{
        List<PlayUrlDTO.VideoDTO> videos = data.getDash().getVideo();
        List<PlayUrlDTO.AudioDTO> audios =data.getDash().getAudio();
        videos = videos.stream()
                .filter(videoDTO -> videoDTO.getCodecId() == 7)
                .sorted((v1, v2) -> Integer.compare(v2.getId(), v1.getId()))
                .collect(Collectors.toList());

        audios = audios.stream()
                .sorted((v1, v2) -> Integer.compare(v2.getId(), v1.getId()))
                .collect(Collectors.toList());

        PlayUrlDTO.VideoDTO video = videos.stream().findFirst().orElse(null);
        PlayUrlDTO.AudioDTO audio = audios.stream().findFirst().orElse(null);

        assert video != null;
        assert audio != null;

        Path videoFolderPath = Paths.get(videoStoragePath.toString(), aid, cid);
        FileUtils.deleteDirectory(videoFolderPath.toFile());
        if (Files.notExists(videoFolderPath)) {
            Files.createDirectories(videoFolderPath);
        }
        Path videoPath = Paths.get(videoFolderPath.toString(), "video");
        Path audioPath = Paths.get(videoFolderPath.toString(), "audio");
        Path finalVideoPath = Paths.get(videoFolderPath.getParent().toString(), String.format("%s.mp4", cid));
        download(video.getBaseUrl(), videoPath, aid);
        download(audio.getBaseUrl(), audioPath, aid);

        ProcessBuilder pb = new ProcessBuilder(ffmpeg,
                "-i", videoPath.toFile().getAbsolutePath(),
                "-i", audioPath.toFile().getAbsolutePath(),
                "-c:v", "copy", "-c:a", "copy", "-strict", "experimental", finalVideoPath.toFile().getAbsolutePath());
        Process p = pb.start();
        int resultCode = p.waitFor();
        if (resultCode != 0) {
            String errMsg = IOUtils.toString(p.getErrorStream(), UTF_8);
            throw new RuntimeException("ffmpeg merge m4s fail. Cause:" + errMsg);
        }

        if (Files.notExists(finalVideoPath)) {
            throw new RuntimeException(finalVideoPath.toString() + "Not exists");
        }
        FileUtils.deleteDirectory(videoFolderPath.toFile());
        return finalVideoPath;
    }

    private Path downloadVideoByDurl(PlayUrlDTO.DataDTO data, String aid, String cid) throws Exception{
        List<PlayUrlDTO.DurlDTO> durls = data.getDurl();

        Path videoFolderPath = Paths.get(videoStoragePath.toString(), aid, cid);
        if (Files.exists(videoFolderPath)) {
            FileUtils.deleteDirectory(videoFolderPath.toFile());
        }

        if (Files.notExists(videoFolderPath)) {
            Files.createDirectories(videoFolderPath);
        }

        durls.sort(Comparator.comparingInt(PlayUrlDTO.DurlDTO::getOrder));
        List<Path> paths =
                durls.parallelStream()
                        .map(durl -> {
                            try {
                                Path path = Paths.get(videoFolderPath.toString(), String.format("%d.flv", durl.getOrder()));
                                download(durl.getUrl(), path, aid);
                                return path;
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            }
                        }).sorted(Comparator.comparingInt(p -> Integer.valueOf(StringUtils.split(p.toFile().getName(), ".")[0]))).collect(Collectors.toList());

        Path inputTxt = Paths.get(videoFolderPath.toString(), "inputs.txt");
        BufferedWriter bufferedWriter = Files.newBufferedWriter(inputTxt);
        paths.forEach(path -> {
            try {
                bufferedWriter.write(String.format("file '%s'", path.toFile().getName()));
                bufferedWriter.newLine();
            }catch (Exception ignore) {

            }
        });
        bufferedWriter.flush();
        bufferedWriter.close();

        //ffmpeg -f concat -i inputs.txt -c copy output.flv
        Path finalVideoPath = Paths.get(videoFolderPath.getParent().toString(), String.format("%s.mp4", cid));

        ProcessBuilder pb = new ProcessBuilder(ffmpeg, "-f", "concat", "-i", inputTxt.toFile().getAbsolutePath(), "-c", "copy", finalVideoPath.toFile().getAbsolutePath());
        Process p = pb.start();
        int resultCode = p.waitFor();
        if (resultCode != 0) {
            String errMsg = IOUtils.toString(p.getErrorStream(), UTF_8);
            throw new RuntimeException("ffmpeg merge m4s fail. Cause:" + errMsg);
        }

        if (Files.notExists(finalVideoPath)) {
            throw new RuntimeException(finalVideoPath.toString() + "Not exists");
        }
        FileUtils.deleteDirectory(videoFolderPath.toFile());
        return finalVideoPath;
    }

    private void download(String url, Path savePath, String aid) throws Exception{
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HttpHeaders.USER_AGENT, USER_AGENT);
        httpGet.setHeader(HttpHeaders.REFERER, String.format("https://www.bilibili.com/video/av%s", aid));

        int maxRetry = 5;
        do {
            try {
                CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);

                if (closeableHttpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    closeableHttpResponse.close();
                    throw new RuntimeException("Http response fail: " + closeableHttpResponse.getStatusLine().getReasonPhrase());
                }

                if (Files.notExists(savePath)) {
                    Files.createFile(savePath);
                }
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                OutputStream os = Files.newOutputStream(savePath, CREATE, TRUNCATE_EXISTING);
                httpEntity.writeTo(os);
                IOUtils.closeQuietly(os);
                closeableHttpResponse.close();
                return;
            }catch (org.apache.http.ConnectionClosedException e){
                if (maxRetry == 1) {
                    logger.error("Retry 3 times but fail({}). aid: {}", e.getMessage(), aid);
                    throw new RuntimeException("Retry many times but fail.");
                }
            }
            maxRetry--;
        }while (maxRetry > 0);
    }

    private String getBulletComment(String cid) throws Exception{
        String bulletCommentUrl = String.format("%s%s.xml", ApiConsts.BULLET_COMMENT_XML_API_URL, cid);
        HttpGet httpGet = new HttpGet(bulletCommentUrl);
        httpGet.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
        httpGet.setHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9,en;q=0.8");
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        if (closeableHttpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            closeableHttpResponse.close();
            throw new RuntimeException(closeableHttpResponse.getStatusLine().getReasonPhrase());
        }
        String s = EntityUtils.toString(closeableHttpResponse.getEntity(), UTF_8);
        closeableHttpResponse.close();
        return s;
    }

    private String refineFileName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return fileName;
        }

        fileName = StringUtils.replace(fileName, "\\", ":");
        fileName = StringUtils.replace(fileName, "/", ":");
        fileName = StringUtils.replace(fileName, "|", "-");
        return fileName;
    }
}
