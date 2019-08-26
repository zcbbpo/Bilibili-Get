package org.bilibili;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Jim
 */
public class HttpDownloadTest {
    public static void main(String[] args) throws Exception {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://upos-hz-mirrorbsyu.acgvideo.com/upgcxcode/80/77/4847780/4847780-2-80.flv?e=ig8euxZM2rNcNbhMhwdVhoMz7wdVhwdEto8g5X10ugNcXBlqNxHxNEVE5XREto8KqJZHUa6m5J0SqE85tZvEuENvNC8xNEVE9EKE9IMvXBvE2ENvNCImNEVEK9GVqJIwqa80WXIekXRE9IMvXBvEuENvNCImNEVEua6m2jIxux0CkF6s2JZv5x0DQJZY2F8SkXKE9IB5QK==&deadline=1559028527&gen=playurl&nbs=1&oi=1960979307&os=bsyu&platform=pc&trid=eea7f84cd796484cbf5717e08ed4cb5a&uipk=5&upsig=d098c136b9e68342c228bb4fb682f7e0&uparams=e,deadline,gen,nbs,oi,os,platform,trid,uipk&mid=0");
        httpGet.setHeader("Host", "upos-hz-mirrorbsyu.acgvideo.com");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
        httpGet.setHeader("Referer", "https://www.bilibili.com/video/av3085994");

        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        httpEntity.writeTo(Files.newOutputStream(Paths.get("/Users/jimcao/test/a/ccccc.flv")));
    }
}
