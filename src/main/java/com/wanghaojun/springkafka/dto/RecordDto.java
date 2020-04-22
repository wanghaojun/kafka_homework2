package com.wanghaojun.springkafka.dto;

import javax.persistence.*;

public class RecordDto {

    public RecordDto() {
    }

    private String userId;

    private String articalId;

    private String type;

    private String time;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticalId() {
        return articalId;
    }

    public void setArticalId(String articalId) {
        this.articalId = articalId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
