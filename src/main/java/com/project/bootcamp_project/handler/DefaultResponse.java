package com.project.bootcamp_project.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;


public class DefaultResponse {

    private static HttpHeaders addHeadersWithCookies(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();

        ResponseCookie refreshCookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60 * 60 * 24 * 7)
                .sameSite("None")
                .build();

        headers.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return headers;
    }

    private static HttpHeaders addHeadersWithRemovedCookies() {
        HttpHeaders headers = new HttpHeaders();

        ResponseCookie removeRefreshCookie = ResponseCookie.from("refresh_token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        headers.add(HttpHeaders.SET_COOKIE, removeRefreshCookie.toString());

        return headers;
    }

    public static ResponseEntity<Object> successWithDataAndHeadersForLogout(Object data, HttpServletRequest request) {
        HttpHeaders headers = addHeadersWithRemovedCookies();

        return ApiResponseHandler.buildResponseWithHeaders(
                "Logout successful",
                HttpStatus.OK,
                data,
                headers,
                request
        );
    }

    public static ResponseEntity<Object> successWithDataAndHeaders(Object data, HttpServletRequest request, String accessToken, String refreshToken) {
        HttpHeaders headers = addHeadersWithCookies(refreshToken);

        return ApiResponseHandler.buildResponseWithHeaders("OPERASI BERHASIL",
                HttpStatus.OK,
                data,
                headers,
                request);
    }

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

    public static ResponseEntity<Object> failedDeleted(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA GAGAL DIHAPUS",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null,
                request);
    }

    public static ResponseEntity<Object> found(Object data, HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA BERHASIL DITEMUKAN",
                HttpStatus.OK,
                data,
                request);
    }

    public static ResponseEntity<Object> foundWithPagination(Object data,
                                                             HttpServletRequest request,
                                                             int currentPage,
                                                             int totalItems,
                                                             int totalPages) {
        return ApiResponseHandler.buildResponseWithPagination("DATA BERHASIL DITEMUKAN",
                HttpStatus.OK,
                data,
                currentPage,
                totalItems,
                totalPages,
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

    public static ResponseEntity<Object> invalidRefreshTokenNotPresent(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("REFRESH TOKEN TIDAK ADA",
                HttpStatus.BAD_REQUEST,
                null,
                request);
    }

    public static ResponseEntity<Object> invalidRefreshToken(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("REFRESH TOKEN TIDAK VALID SILAHKAN LOGIN ULANG",
                HttpStatus.FORBIDDEN,
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

    public static ResponseEntity<Object> badRequest(HttpServletRequest request) {
        return ApiResponseHandler.buildResponse("DATA TIDAK VALID",
                HttpStatus.BAD_REQUEST,
                null,
                request);
    }
}
