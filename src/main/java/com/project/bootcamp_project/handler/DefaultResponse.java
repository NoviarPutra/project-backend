package com.project.bootcamp_project.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DefaultResponse {
    public static ResponseEntity<Object> saved(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA BERHASIL DISIMPAN",
                HttpStatus.CREATED,
                null,
                request);

    }

    public static ResponseEntity<Object> failedSaved(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA GAGAL DISIMPAN",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null,
                request);
    }

    public static ResponseEntity<Object> updated(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA BERHASIL DIUPDATE",
                HttpStatus.OK,
                null,
                request);
    }

    public static ResponseEntity<Object> failedUpdated(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA GAGAL DIUPDATE",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null,
                request);
    }

    public static ResponseEntity<Object> deleted(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA BERHASIL DIHAPUS",
                HttpStatus.OK,
                null,
                request);
    }

    public static ResponseEntity<Object> found(Object data, HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA BERHASIL DITEMUKAN",
                HttpStatus.OK,
                data,
                request);
    }

    public static ResponseEntity<Object> notFound(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA TIDAK DITEMUKAN",
                HttpStatus.NOT_FOUND,
                null,
                request);
    }

    public static ResponseEntity<Object> alreadyExists(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA SUDAH ADA",
                HttpStatus.CONFLICT,
                null,
                request);
    }

    public static ResponseEntity<Object> invalidCredential(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("CREDENTIAL TIDAK VALID",
                HttpStatus.UNAUTHORIZED,
                null,
                request);
    }

    public static ResponseEntity<Object> successWithData(Object data, HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("OPERASI BERHASIL",
                HttpStatus.OK,
                data,
                request);
    }

    public static ResponseEntity<Object> failed(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("OPERASI GAGAL",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null,
                request);
    }

}
