package com.tenpo.challenge.challenge.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.challenge.challenge.adapter.controller.model.out.ErrorResponse;
import com.tenpo.challenge.challenge.application.port.out.RateLimiterPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GlobalRateLimitingFilter extends OncePerRequestFilter {
    private final RateLimiterPort rateLimiterPort;
    private final ObjectMapper objectMapper;

    public GlobalRateLimitingFilter(RateLimiterPort rateLimiterPort){
        this.rateLimiterPort = rateLimiterPort;
        this.objectMapper = new ObjectMapper();
    }
    private static final String ERROR_MESSAGE = "You have exceeded the maximum number of requests per minute.";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (rateLimiterPort.isAllowed()) {
            filterChain.doFilter(request, response);
        } else {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.TOO_MANY_REQUESTS.value())
                    .message(ERROR_MESSAGE)
                    .timestamp(System.currentTimeMillis())
                    .build();

            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json");

            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
    }
}
