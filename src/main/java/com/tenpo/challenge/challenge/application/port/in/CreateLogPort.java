package com.tenpo.challenge.challenge.application.port.in;

import org.aspectj.lang.ProceedingJoinPoint;

public interface CreateLogPort {
    void saveLogAsync(ProceedingJoinPoint joinPoint, String method, Object response);
}
