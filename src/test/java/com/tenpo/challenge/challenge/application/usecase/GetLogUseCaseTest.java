package com.tenpo.challenge.challenge.application.usecase;

import com.tenpo.challenge.challenge.application.port.out.LogJdbc;
import com.tenpo.challenge.challenge.domain.LogDomain;
import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.when;

class GetLogUseCaseTest {
    private LogJdbc logJdbc;
    private GetLogUseCase getLogUseCase;

    private MockFactoryDomain mockFactoryDomain;

    @BeforeEach
    void setUp() {
        logJdbc = Mockito.mock(LogJdbc.class);
        getLogUseCase = new GetLogUseCase(logJdbc);
        mockFactoryDomain = new MockFactoryDomain();
    }

    @Test
    @DisplayName("Execute get logs")
    void testExecuteSuccess() {
        Page<LogDomain> logsDomain = mockFactoryDomain.getPageLogDomain();
        Pageable pageable = PageRequest.of(1, 1);
        when(logJdbc.findAllLog(pageable)).thenReturn(logsDomain);

        Page<LogDomain> result = getLogUseCase.execute(pageable);

        Assertions.assertEquals(result.getSize(), logsDomain.getSize());
        Assertions.assertEquals(result.getTotalElements(), logsDomain.getTotalElements());
        Assertions.assertEquals(result.getTotalPages(), logsDomain.getTotalPages());
    }
}
