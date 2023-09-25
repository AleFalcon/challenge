package com.tenpo.challenge.challenge.application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tenpo.challenge.challenge.application.port.out.RateLimiterPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.servlet.FilterChain;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class GlobalRateLimitingFilterTest {
    private GlobalRateLimitingFilter globalRateLimitingFilter;
    private RateLimiterPort rateLimiterPort;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        rateLimiterPort = Mockito.mock(RateLimiterPort.class);

        globalRateLimitingFilter = new GlobalRateLimitingFilter(rateLimiterPort);
    }

    @Test
    void testDoFilterInternal() throws IOException, ServletException {
        FilterChain filterChain = Mockito.mock(FilterChain.class);

        when(rateLimiterPort.isAllowed()).thenReturn(false);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        globalRateLimitingFilter.doFilterInternal(request, response, filterChain);

        verify(response).setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        verify(response).setContentType("application/json");
    }
}
