package com.laputa.iot.msg.api.dto;

import java.io.Serializable;

public class ResponseMessage implements Serializable {
    private static final long serialVersionUID = 734834001120103346L;
    private String content;

    public ResponseMessage() {
    }

    public ResponseMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
