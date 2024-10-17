package com.project.bootcamp_project.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bootcamp_project.dto.response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {

        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ApiResponse apiResponse = new ApiResponse(
                "Error",
                null,
                "AKSES DITOLAK KARENA TOKEN TIDAK VALID / SESI KEDALUWARSA",
                new HashMap<>()
        );
        response.getOutputStream().println(mapper.writeValueAsString(apiResponse));
    }
}