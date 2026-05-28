package com.taskapi.taskapi.clases;

import java.io.IOException;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;


import com.taskapi.taskapi.clases.service.LogService;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class LoggingFilter extends OncePerRequestFilter {

    private final LogService logService;

    public LoggingFilter(LogService logService) {
        this.logService = logService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        filterChain.doFilter(request, response);

        String method = request.getMethod();

        String path = request.getRequestURI();

        int status = response.getStatus();

        logService.sendLog(method, path, status);
    }
}