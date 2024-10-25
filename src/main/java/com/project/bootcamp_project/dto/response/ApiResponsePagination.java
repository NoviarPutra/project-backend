package com.project.bootcamp_project.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class ApiResponsePagination {

    private String status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String path;

    private String message;
    private Date timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer currentPage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalItems;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPages;

    private Object data;


    public ApiResponsePagination(String status, String path, String message, Integer currentPage, Integer totalItems, Integer totalPages, Object data) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.timestamp = new Date();
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.data = data;
    }

    // Getter dan Setter untuk semua field
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
