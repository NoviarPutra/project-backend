package com.project.bootcamp_project.handler;

import com.project.bootcamp_project.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ApiResponseHandler {
    public static ResponseEntity<Object> buildResponse(String message, HttpStatus status, Object data,
                                                       HttpServletRequest request) {
        String statusText = status.isError() ? "Error" : "Success";
        String path = null;
        if (status.is4xxClientError() || status.is5xxServerError()) {
            path = request.getRequestURI();
        }
        Object responseData = (data == null) ? new HashMap<>() : data;
        ApiResponse response = new ApiResponse(
                statusText,
                path,
                message,
                responseData);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Object> buildResponseWithHeaders(String message, HttpStatus status, Object data,
                                                                  HttpHeaders headers, HttpServletRequest request) {
        String statusText = status.isError() ? "Error" : "Success";
        String path = null;
        if (status.is4xxClientError() || status.is5xxServerError()) {
            path = request.getRequestURI();
        }
        Object responseData = (data == null) ? new HashMap<>() : data;
        ApiResponse response = new ApiResponse(
                statusText,
                path,
                message,
                responseData);

        return new ResponseEntity<>(response, headers, status);
    }
}
