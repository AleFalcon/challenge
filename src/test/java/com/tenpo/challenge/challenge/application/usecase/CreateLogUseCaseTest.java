package com.tenpo.challenge.challenge.application.usecase;

import com.tenpo.challenge.challenge.application.port.out.LogJdbc;
import com.tenpo.challenge.challenge.domain.LogEntity;
import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.aspectj.lang.Signature;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class CreateLogUseCaseTest {
    private LogJdbc logJdbc;
    private CreateLogUseCase createLogUseCase;
    private MockFactoryDomain mockFactoryDomain;

    @BeforeEach
    void setUp() {
        logJdbc = Mockito.mock(LogJdbc.class);
        mockFactoryDomain = new MockFactoryDomain();
        createLogUseCase = new CreateLogUseCase(logJdbc);
    }

    @Test
    void testSaveLogAsync() throws Throwable {
        ProceedingJoinPoint joinPoint = Mockito.mock(ProceedingJoinPoint.class);
        Signature signature = Mockito.mock(Signature.class);
        LogEntity logEntity = mockFactoryDomain.getLogEntity();

        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn(logEntity.getEndpoint());

        createLogUseCase.saveLogAsync(joinPoint, "GET", logEntity.getResponseBody());

        Thread.sleep(5);

        verify(logJdbc, times(1)).saveLogAsync(eq(logEntity.getEndpoint()), eq(logEntity.getHttpMethod()), eq(logEntity.getResponseBody()));
    }
}
