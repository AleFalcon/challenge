package com.tenpo.challenge.challenge.domain;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GlobalRateLimiter implements RateLimiter {
    private final AtomicInteger tokens;
    private final int maxTokens;
    private final ScheduledExecutorService scheduler;

    public GlobalRateLimiter(int maxTokensPerMinute) {
        this.tokens = new AtomicInteger(maxTokensPerMinute);
        this.maxTokens = maxTokensPerMinute;
        this.scheduler = new ScheduledThreadPoolExecutor(1);
        this.scheduler.scheduleAtFixedRate(this::refill, 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public boolean isAllowed() {
        return tokens.getAndDecrement() > 0;
    }

    private void refill() {
        tokens.set(maxTokens);
    }
}
