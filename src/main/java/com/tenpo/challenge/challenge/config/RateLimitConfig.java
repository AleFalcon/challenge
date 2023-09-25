package com.tenpo.challenge.challenge.config;

import com.tenpo.challenge.challenge.adapter.RateLimiterAdapter;
import com.tenpo.challenge.challenge.application.GlobalRateLimitingFilter;
import com.tenpo.challenge.challenge.domain.GlobalRateLimiter;
import com.tenpo.challenge.challenge.application.port.out.RateLimiterPort;
import com.tenpo.challenge.challenge.domain.RateLimiter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitConfig {
    @Bean
    public RateLimiter rateLimiter() {return new GlobalRateLimiter(3); }

    @Bean
    public RateLimiterPort rateLimiterPort() {
        return new RateLimiterAdapter(rateLimiter());
    }

    @Bean
    public FilterRegistrationBean<GlobalRateLimitingFilter> globalRateLimitingFilter() {
        FilterRegistrationBean<GlobalRateLimitingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new GlobalRateLimitingFilter(rateLimiterPort()));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
