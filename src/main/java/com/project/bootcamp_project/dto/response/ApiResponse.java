package com.project.bootcamp_project.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class ApiResponse {

    private String status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errorCode;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String path;

    private String message;
    private Date timestamp;

    private Object data;

    public ApiResponse(String status, String errorCode, String path, String message, Object data) {
        this.status = status;
        this.errorCode = errorCode;
        this.path = path;
        this.message = message;
        this.timestamp = new Date();
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
