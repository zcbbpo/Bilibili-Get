package org.bilibili.get.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.bilibili.get.util.InfoValueDeserializer;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * @author Jim
 */
public class BulletCommentDTO {
    @JSONField(name = "chatserver")
    private String chatServer;
    @JSONField(name = "chatid")
    private Long chatId;
    private Integer mission;
    @JSONField(name = "maxlimit")
    private Integer maxLimit;
    private Integer state;
    @JSONField(name = "real_name")
    private Integer realName;
    private String source;
    @JSONField(name = "d")
    private List<CommentDTO> comments;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public String getChatServer() {
        return chatServer;
    }

    public void setChatServer(String chatServer) {
        this.chatServer = chatServer;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Integer getMission() {
        return mission;
    }

    public void setMission(Integer mission) {
        this.mission = mission;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRealName() {
        return realName;
    }

    public void setRealName(Integer realName) {
        this.realName = realName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public static class CommentDTO {
        @JSONField(name = "p", deserializeUsing = InfoValueDeserializer.class)
        private CommentInfoDTO commentInfo;
        private String content;

        public CommentInfoDTO getCommentInfo() {
            return commentInfo;
        }

        public void setCommentInfo(CommentInfoDTO commentInfo) {
            this.commentInfo = commentInfo;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class CommentInfoDTO {
        private Integer time;
        private String userId;

        public Integer getTime() {
            return time;
        }

        public void setTime(Integer time) {
            this.time = time;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

}
