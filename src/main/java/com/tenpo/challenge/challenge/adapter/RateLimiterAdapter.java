package com.tenpo.challenge.challenge.adapter;

import com.tenpo.challenge.challenge.application.port.out.RateLimiterPort;
import com.tenpo.challenge.challenge.domain.RateLimiter;

public class RateLimiterAdapter implements RateLimiterPort {
    private final RateLimiter rateLimiter;

    public RateLimiterAdapter(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public boolean isAllowed() {
        return rateLimiter.isAllowed();
    }
}
