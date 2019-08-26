package org.bilibili.get.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author Jim
    aid: 52914112
    author: "渗透之C君"
    comment: 2794
    copyright: ""
    created: 1558236927
    description: "自制翻译！《Fran Bow》（《弗兰的悲惨之旅》作者终于出新作了！依然是猎奇+黑暗童话风格的2D恐怖游戏：主角是一名8岁小女孩，今天却是她生命的最后一天…这一天，她将和她的朋友Mr. Voice一起进行一场大冒险！）作者是瑞典一对神仙夫妻！↵（5月18日投错了稿件，这个是重投）↵★本期收藏过5万，未来游戏正式版发布时第一时间出实况，如果没有官中就自制字幕！↵★微博@渗透之C菌↵★Instagram@roshinich"
    favorites: 37828
    hide_click: false
    is_pay: 0
    is_union_video: 0
    length: "23:19"
    mid: 4162287
    pic: "//i1.hdslb.com/bfs/archive/d59527f684255a243d9c777eef04903f4870a85c.jpg"
    play: 519212
    review: 0
    subtitle: ""
    title: "【C菌】在生命的最后一天, 她竟做了这样的选择!《Little Misfortune》实况/中文字幕"
    typeid: 17
    video_review: 8813
 */
public class CommentDTO {

    private String aid;
    private String author;
    private String copyright;
    @JSONField(deserializeUsing = CreatedValueDeserializer.class)
    private Date created;
    private String description;
    private Integer favorites;
    @JSONField(name = "hide_click")
    private Boolean hideClick;
    @JSONField(name = "is_pay")
    private Boolean pay;
    @JSONField(name = "is_union_video")
    private Boolean unionVideo;
    private String length;
    private String mid;
    private String pic;
    @JSONField(deserializeUsing = PlayValueDeserializer.class)
    private Integer play;
    private Integer review;
    private String subtitle;
    private String title;
    @JSONField(name = "typeid")
    private Short typeId;
    @JSONField(name = "video_review")
    private Integer videoReview;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFavorites() {
        return favorites;
    }

    public void setFavorites(Integer favorites) {
        this.favorites = favorites;
    }

    public Boolean getHideClick() {
        return hideClick;
    }

    public void setHideClick(Boolean hideClick) {
        this.hideClick = hideClick;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    public Boolean getUnionVideo() {
        return unionVideo;
    }

    public void setUnionVideo(Boolean unionVideo) {
        this.unionVideo = unionVideo;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getPlay() {
        return play;
    }

    public void setPlay(Integer play) {
        this.play = play;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Short getTypeId() {
        return typeId;
    }

    public void setTypeId(Short typeId) {
        this.typeId = typeId;
    }

    public Integer getVideoReview() {
        return videoReview;
    }

    public void setVideoReview(Integer videoReview) {
        this.videoReview = videoReview;
    }

    public static class PlayValueDeserializer implements ObjectDeserializer{
        @SuppressWarnings("unchecked")
        @Override
        public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
            try {
                return (T)parser.parseObject(int.class);
            }catch (Exception e) {
                return (T)Integer.valueOf(0);
            }
        }

        @Override
        public int getFastMatchToken() {
            return JSONToken.LITERAL_INT;
        }
    }

    public static class CreatedValueDeserializer implements ObjectDeserializer{
        @SuppressWarnings("unchecked")
        @Override
        public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
            Long l = parser.parseObject(long.class);
            return (T)new Date(l * 1000L);
        }

        @Override
        public int getFastMatchToken() {
            return JSONToken.LITERAL_ISO8601_DATE;
        }
    }
}
