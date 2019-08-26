package org.bilibili.get.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Jim
 */
public class PageListDTO {
    private Integer page;
    @JSONField(name = "pagename")
    private String pageName;
    private Integer cid;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
