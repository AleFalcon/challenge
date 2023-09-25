package com.tenpo.challenge.challenge.adapter.controller.aspects;

import com.tenpo.challenge.challenge.application.port.in.CreateLogPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {
    private final HttpServletRequest httpServletRequest;
    private final CreateLogPort createLogPort;

    @Pointcut("execution(public * com.tenpo.challenge.challenge.adapter.controller..*(..))")
    private void anyControllerMethod() {}

    @Around("anyControllerMethod()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object response = joinPoint.proceed();
        try {
            createLogPort.saveLogAsync(joinPoint, httpServletRequest.getMethod(), response);
        } catch (Exception e) {
            log.error("Call history saving failed.");
        }

        return response;
    }
}
