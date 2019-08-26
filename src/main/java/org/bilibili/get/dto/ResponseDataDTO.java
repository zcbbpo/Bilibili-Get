package org.bilibili.get.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author Jim
    count: 217
    pages: 8
    tlist: {4: {tid: 4, count: 206, name: "游戏"}, 160: {tid: 160, count: 11, name: "生活"}}
    vlist: [{comment: 2794, typeid: 17, play: 519212,…}, {comment: 5626, typeid: 21, play: 1301725,…},…]
 */
public class ResponseDataDTO {

    private Integer count;
    private Integer pages;
    @JSONField(name = "vlist")
    private List<CommentDTO> commentList;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<CommentDTO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDTO> commentList) {
        this.commentList = commentList;
    }
}
