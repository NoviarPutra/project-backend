package com.project.bootcamp_project.core;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request != null) {
            String contentType = request.getContentType();
            if (contentType != null && contentType.contains("multipart/form-data")) {
                request = new MyHttpServletRequestWrapper((HttpServletRequest) request);
            }
        }
        super.doFilter(request, response, chain);
    }
}
