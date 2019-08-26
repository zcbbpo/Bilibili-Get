package org.bilibili.get.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author Jim
 */
public class PlayUrlDTO {
    private Integer code;
    private String message;
    private Integer ttl;
    private DataDTO data;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    /**
     "from": "local",
     "result": "suee",
     "message": "",
     "quality": 16,
     "format": "flv360",
     "timelength": 657411,
     "accept_format": "flv_p60,flv720_p60,flv,flv720,flv480,flv360",
     "accept_description": [
     "高清 1080P60",
     "高清 720P60",
     "高清 1080P",
     "高清 720P",
     "清晰 480P",
     "流畅 360P"
     ],
     "accept_quality": [
     116,
     74,
     80,
     64,
     32,
     16
     ],
     "video_codecid": 7,
     "seek_param": "start",
     "seek_type": "offset",
     */
    public static class DataDTO {
        private String from;
        private String result;
        private String message;
        private Integer quality;
        private String format;
        @JSONField(name = "timelength")
        private Integer timeLength;
        @JSONField(name = "accept_format")
        private String acceptFormat;
        @JSONField(name = "accept_description")
        private List<String> acceptDescription;
        @JSONField(name = "accept_quality")
        private List<Integer> acceptQuality;
        @JSONField(name = "video_codecid")
        private Integer videCodecId;
        @JSONField(name = "seek_param")
        private String seekParam;
        @JSONField(name = "seek_type")
        private String seekType;
        private DashDTO dash;
        private List<DurlDTO> durl;

        public List<DurlDTO> getDurl() {
            return durl;
        }

        public void setDurl(List<DurlDTO> durl) {
            this.durl = durl;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getQuality() {
            return quality;
        }

        public void setQuality(Integer quality) {
            this.quality = quality;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public Integer getTimeLength() {
            return timeLength;
        }

        public void setTimeLength(Integer timeLength) {
            this.timeLength = timeLength;
        }

        public String getAcceptFormat() {
            return acceptFormat;
        }

        public void setAcceptFormat(String acceptFormat) {
            this.acceptFormat = acceptFormat;
        }

        public List<String> getAcceptDescription() {
            return acceptDescription;
        }

        public void setAcceptDescription(List<String> acceptDescription) {
            this.acceptDescription = acceptDescription;
        }

        public List<Integer> getAcceptQuality() {
            return acceptQuality;
        }

        public void setAcceptQuality(List<Integer> acceptQuality) {
            this.acceptQuality = acceptQuality;
        }

        public Integer getVideCodecId() {
            return videCodecId;
        }

        public void setVideCodecId(Integer videCodecId) {
            this.videCodecId = videCodecId;
        }

        public String getSeekParam() {
            return seekParam;
        }

        public void setSeekParam(String seekParam) {
            this.seekParam = seekParam;
        }

        public String getSeekType() {
            return seekType;
        }

        public void setSeekType(String seekType) {
            this.seekType = seekType;
        }

        public DashDTO getDash() {
            return dash;
        }

        public void setDash(DashDTO dash) {
            this.dash = dash;
        }
    }

    /**
     "duration":658,
     "minBufferTime":1.5,
     "min_buffer_time":1.5,
     */
    public static class DashDTO {
        private Integer duration;
        private float minBufferTime;
        private List<VideoDTO> video;
        private List<AudioDTO> audio;

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public float getMinBufferTime() {
            return minBufferTime;
        }

        public void setMinBufferTime(float minBufferTime) {
            this.minBufferTime = minBufferTime;
        }

        public List<VideoDTO> getVideo() {
            return video;
        }

        public void setVideo(List<VideoDTO> video) {
            this.video = video;
        }

        public List<AudioDTO> getAudio() {
            return audio;
        }

        public void setAudio(List<AudioDTO> audio) {
            this.audio = audio;
        }
    }


    /**
     * "id": 16,
     "baseUrl": "http://cn-zjwz3-dx-v-05.acgvideo.com/upgcxcode/69/92/93089269/93089269_nb2-1-30011.m4s?expires=1558604400&platform=pc&ssig=gxWTyb-yUJkylDARj3dYPw&oi=1960979307&trid=8d409230f1404c3e81a6a5c894d8e1de&nfb=maPYqpoel5MI3qOUX6YpRA==&nfc=1",
     "base_url": "http://cn-zjwz3-dx-v-05.acgvideo.com/upgcxcode/69/92/93089269/93089269_nb2-1-30011.m4s?expires=1558604400&platform=pc&ssig=gxWTyb-yUJkylDARj3dYPw&oi=1960979307&trid=8d409230f1404c3e81a6a5c894d8e1de&nfb=maPYqpoel5MI3qOUX6YpRA==&nfc=1",
     "backupUrl": [
     "http://cn-zjjh5-dx-v-03.acgvideo.com/upgcxcode/69/92/93089269/93089269_nb2-1-30011.m4s?expires=1558604400&platform=pc&ssig=gxWTyb-yUJkylDARj3dYPw&oi=1960979307&trid=8d409230f1404c3e81a6a5c894d8e1de&nfb=maPYqpoel5MI3qOUX6YpRA==&nfc=1",
     "http://upos-hz-mirrorbosu.acgvideo.com/upgcxcode/69/92/93089269/93089269_nb2-1-30011.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEVEuxTEto8i8o859r1qXg8xNEVE5XREto8GuFGv2U7SuxI72X6fTr859r1qXg8gNEVE5XREto8z5JZC2X2gkX5L5F1eTX1jkXlsTXHeux_f2o859IB_&deadline=1558604615&gen=playurl&nbs=1&oi=1960979307&os=bosu&platform=pc&trid=8d409230f1404c3e81a6a5c894d8e1de&uipk=5&upsig=70d3041b12d90f3183424a0d37d848cc&uparams=e,deadline,gen,nbs,oi,os,platform,trid,uipk"
     ],
     "backup_url": [
     "http://cn-zjjh5-dx-v-03.acgvideo.com/upgcxcode/69/92/93089269/93089269_nb2-1-30011.m4s?expires=1558604400&platform=pc&ssig=gxWTyb-yUJkylDARj3dYPw&oi=1960979307&trid=8d409230f1404c3e81a6a5c894d8e1de&nfb=maPYqpoel5MI3qOUX6YpRA==&nfc=1",
     "http://upos-hz-mirrorbosu.acgvideo.com/upgcxcode/69/92/93089269/93089269_nb2-1-30011.m4s?e=ig8euxZM2rNcNbdlhoNvNC8BqJIzNbfqXBvEqxTEto8BTrNvN0GvT90W5JZMkX_YN0MvXg8gNEVEuxTEto8i8o859r1qXg8xNEVE5XREto8GuFGv2U7SuxI72X6fTr859r1qXg8gNEVE5XREto8z5JZC2X2gkX5L5F1eTX1jkXlsTXHeux_f2o859IB_&deadline=1558604615&gen=playurl&nbs=1&oi=1960979307&os=bosu&platform=pc&trid=8d409230f1404c3e81a6a5c894d8e1de&uipk=5&upsig=70d3041b12d90f3183424a0d37d848cc&uparams=e,deadline,gen,nbs,oi,os,platform,trid,uipk"
     ],
     "bandwidth": 276356,
     "mimeType": "video/mp4",
     "mime_type": "video/mp4",
     "codecs": "hev1.1.6.L120.90",
     "width": 640,
     "height": 360,
     "frameRate": "16000/544",
     "frame_rate": "16000/544",
     "sar": "1:1",
     "startWithSap": 1,
     "start_with_sap": 1,
     "SegmentBase": {
     "Initialization": "0-1172",
     "indexRange": "1173-2776"
     },
     "segment_base": {
     "initialization": "0-1172",
     "index_range": "1173-2776"
     },
     "codecid": 12
     */
    public static class VideoDTO {
        private Integer id;
        private String baseUrl;
        private List<String> backupUrl;
        @JSONField(name = "bandwidth")
        private Integer bandWidth;
        @JSONField(name = "mimeType")
        private String mimeType;
        @JSONField(name = "codecs")
        private String codecs;
        @JSONField(name = "width")
        private Integer width;
        @JSONField(name = "height")
        private Integer height;
        @JSONField(name = "frameRate")
        private String frameRate;
        private String sar;
        private Integer startWithSap;
        @JSONField(name = "SegmentBase")
        private SegmentBaseDTO segmentBase;
        @JSONField(name = "codecid")
        private Integer codecId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public List<String> getBackupUrl() {
            return backupUrl;
        }

        public void setBackupUrl(List<String> backupUrl) {
            this.backupUrl = backupUrl;
        }

        public Integer getBandWidth() {
            return bandWidth;
        }

        public void setBandWidth(Integer bandWidth) {
            this.bandWidth = bandWidth;
        }

        public String getMimeType() {
            return mimeType;
        }

        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        public String getCodecs() {
            return codecs;
        }

        public void setCodecs(String codecs) {
            this.codecs = codecs;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getFrameRate() {
            return frameRate;
        }

        public void setFrameRate(String frameRate) {
            this.frameRate = frameRate;
        }

        public String getSar() {
            return sar;
        }

        public void setSar(String sar) {
            this.sar = sar;
        }

        public Integer getStartWithSap() {
            return startWithSap;
        }

        public void setStartWithSap(Integer startWithSap) {
            this.startWithSap = startWithSap;
        }

        public SegmentBaseDTO getSegmentBase() {
            return segmentBase;
        }

        public void setSegmentBase(SegmentBaseDTO segmentBase) {
            this.segmentBase = segmentBase;
        }

        public Integer getCodecId() {
            return codecId;
        }

        public void setCodecId(Integer codecId) {
            this.codecId = codecId;
        }
    }

    public static class SegmentBaseDTO {
        @JSONField(name = "Initialization")
        private String initialization;
        private String indexRange;

        public String getInitialization() {
            return initialization;
        }

        public void setInitialization(String initialization) {
            this.initialization = initialization;
        }

        public String getIndexRange() {
            return indexRange;
        }

        public void setIndexRange(String indexRange) {
            this.indexRange = indexRange;
        }
    }

    /**
     "id": 30280,
     "baseUrl": "http://cn-jszj-dx-v-01.acgvideo.com/upgcxcode/69/92/93089269/93089269-1-30280.m4s?expires=1558604400&platform=pc&ssig=-rjBX8khFeXl_QnIBQ4LEw&oi=1960979307&trid=8d409230f1404c3e81a6a5c894d8e1de&nfb=maPYqpoel5MI3qOUX6YpRA==&nfc=1",
     "base_url": "http://cn-jszj-dx-v-01.acgvideo.com/upgcxcode/69/92/93089269/93089269-1-30280.m4s?expires=1558604400&platform=pc&ssig=-rjBX8khFeXl_QnIBQ4LEw&oi=1960979307&trid=8d409230f1404c3e81a6a5c894d8e1de&nfb=maPYqpoel5MI3qOUX6YpRA==&nfc=1",
     "backupUrl": [
     "http://cn-zjwz4-dx-v-01.acgvideo.com/upgcxcode/69/92/93089269/93089269-1-30280.m4s?expires=1558604400&platform=pc&ssig=-rjBX8khFeXl_QnIBQ4LEw&oi=1960979307&trid=8d409230f1404c3e81a6a5c894d8e1de&nfb=maPYqpoel5MI3qOUX6YpRA==&nfc=1",
     "http://cn-jszj-dx-v-11.acgvideo.com/upgcxcode/69/92/93089269/93089269-1-30280.m4s?expires=1558604400&platform=pc&ssig=-rjBX8khFeXl_QnIBQ4LEw&oi=1960979307&trid=8d409230f1404c3e81a6a5c894d8e1de&nfb=maPYqpoel5MI3qOUX6YpRA==&nfc=1"
     ],
     "backup_url": [
     "http://cn-zjwz4-dx-v-01.acgvideo.com/upgcxcode/69/92/93089269/93089269-1-30280.m4s?expires=1558604400&platform=pc&ssig=-rjBX8khFeXl_QnIBQ4LEw&oi=1960979307&trid=8d409230f1404c3e81a6a5c894d8e1de&nfb=maPYqpoel5MI3qOUX6YpRA==&nfc=1",
     "http://cn-jszj-dx-v-11.acgvideo.com/upgcxcode/69/92/93089269/93089269-1-30280.m4s?expires=1558604400&platform=pc&ssig=-rjBX8khFeXl_QnIBQ4LEw&oi=1960979307&trid=8d409230f1404c3e81a6a5c894d8e1de&nfb=maPYqpoel5MI3qOUX6YpRA==&nfc=1"
     ],
     "bandwidth": 321401,
     "mimeType": "audio/mp4",
     "mime_type": "audio/mp4",
     "codecs": "mp4a.40.2",
     "width": 0,
     "height": 0,
     "frameRate": "",
     "frame_rate": "",
     "sar": "",
     "startWithSap": 0,
     "start_with_sap": 0,
     "SegmentBase": {
     "Initialization": "0-907",
     "indexRange": "908-2523"
     },
     "segment_base": {
     "initialization": "0-907",
     "index_range": "908-2523"
     },
     "codecid": 0
     */
    public static class AudioDTO extends VideoDTO {

    }

    /**
     "order": 1,
     "length": 439083,
     "size": 16721415,
     "url": "http://upos-hz-mirrorks3u.acgvideo.com/upgcxcode/71/08/1600871/1600871-1-80.flv?e=ig8euxZM2rNcNbeBhwdVtWeBhwdVNEVEuCIv29hEn0lqXg8Y2ENvNCImNEVEUJ1miI7MT96fqj3E9r1qNCNEto8g2ENvN03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6gqj0eN0B599M=&deadline=1558671169&gen=playurl&nbs=1&oi=1960979307&os=ks3u&platform=pc&trid=f83a865b26d047cb97dcfc60904c0df5&uipk=5&upsig=edfc9ea98de2cdc189868d4ee22f1fae&uparams=e,deadline,gen,nbs,oi,os,platform,trid,uipk&mid=0",
     "backup_url": [
     "http://upos-hz-mirrorcosu.acgvideo.com/upgcxcode/71/08/1600871/1600871-1-80.flv?e=ig8euxZM2rNcNbeBhwdVtWeBhwdVNEVEuCIv29hEn0lqXg8Y2ENvNCImNEVEUJ1miI7MT96fqj3E9r1qNCNEto8g2ENvN03eN0B5tZlqNxTEto8BTrNvNeZVuJ10Kj_g2UB02J0mN0B5tZlqNCNEto8BTrNvNC7MTX502C8f2jmMQJ6mqF2fka1mqx6gqj0eN0B599M=&deadline=1558671169&gen=playurl&nbs=1&oi=1960979307&os=cosu&platform=pc&trid=f83a865b26d047cb97dcfc60904c0df5&uipk=5&upsig=efd2fe7c49247a8af3045cea242eea2e&uparams=e,deadline,gen,nbs,oi,os,platform,trid,uipk&mid=0"
     ]
     */
    public static class DurlDTO {
        private Integer order;
        private Long length;
        private Long size;
        private String url;
        @JSONField(name = "backup_url")
        private List<String> backupUrl;

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Long getLength() {
            return length;
        }

        public void setLength(Long length) {
            this.length = length;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getBackupUrl() {
            return backupUrl;
        }

        public void setBackupUrl(List<String> backupUrl) {
            this.backupUrl = backupUrl;
        }
    }
}
