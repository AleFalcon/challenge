package com.tenpo.challenge.challenge.domain;

public interface RateLimiter {
    boolean isAllowed();
}