package com.project.bootcamp_project.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DefaultResponse {
    public static ResponseEntity<Object> saved(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA BERHASIL DISIMPAN",
                HttpStatus.CREATED,
                null,
                null,
                request
        );

    }

    public static ResponseEntity<Object> failedSaved(String errorCode, HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA GAGAL DISIMPAN",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null,
                errorCode,
                request
        );
    }

    public static ResponseEntity<Object> updated(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA BERHASIL DIUPDATE",
                HttpStatus.OK,
                null,
                null,
                request
        );
    }


    public static ResponseEntity<Object> failedUpdated(String errorCode, HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA GAGAL DIUPDATE",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null,
                errorCode,
                request
        );
    }

    public static ResponseEntity<Object> deleted(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA BERHASIL DIHAPUS",
                HttpStatus.OK,
                null,
                null,
                request
        );
    }


    public static ResponseEntity<Object> found(Object data, HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA BERHASIL DITEMUKAN",
                HttpStatus.OK,
                data,
                null,
                request
        );
    }

    public static ResponseEntity<Object> notFound(String errorCode, HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA TIDAK DITEMUKAN",
                HttpStatus.NOT_FOUND,
                null,
                errorCode,
                request
        );
    }

    public static ResponseEntity<Object> alreadyExists(String errorCode, HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA SUDAH ADA",
                HttpStatus.CONFLICT,
                null,
                errorCode,
                request
        );
    }


}
