package org.bilibili.get.dto;

import com.alibaba.fastjson.JSON;

/**
 * @author Jim
 */
public class ResponseDTO {

    private Boolean status;

    private ResponseDataDTO data;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ResponseDataDTO getData() {
        return data;
    }

    public void setData(ResponseDataDTO data) {
        this.data = data;
    }
}
