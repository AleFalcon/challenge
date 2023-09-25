package com.tenpo.challenge.challenge.application.usecase;

import com.tenpo.challenge.challenge.application.port.in.CreateLogPort;
import com.tenpo.challenge.challenge.application.port.out.LogJdbc;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CreateLogUseCase implements CreateLogPort {
    private final LogJdbc logJdbc;
    private static final String EMPTY = "";

    public CreateLogUseCase(LogJdbc logJdbc) {
        this.logJdbc = logJdbc;
    }

    @Async
    @Override
    public void saveLogAsync(ProceedingJoinPoint joinPoint, String method, Object response) {
        logJdbc.saveLogAsync(joinPoint.getSignature().getName(), method, (response != null) ? response.toString() : EMPTY);
    }
}
