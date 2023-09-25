package com.tenpo.challenge.challenge.adapter.jdbc;

import com.tenpo.challenge.challenge.application.port.out.LogRepository;
import com.tenpo.challenge.challenge.domain.LogDomain;
import com.tenpo.challenge.challenge.domain.LogEntity;
import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.mockito.ArgumentCaptor;


class LogJdbcAdapterTest {
    private LogRepository logRepository;
    private MockFactoryDomain mockFactoryDomain;
    private LogJdbcAdapter logJdbcAdapter;

    @BeforeEach
    void setUp() {
        logRepository = Mockito.mock(LogRepository.class);
        logJdbcAdapter = new LogJdbcAdapter(logRepository);
        mockFactoryDomain = new MockFactoryDomain();
    }

    @Test
    void testFindAllLogSuccess() {
        Page<LogEntity> logsEntity = mockFactoryDomain.getPageLogEntity();
        Pageable pageable = PageRequest.of(1, 1);
        when(logRepository.findAll(pageable)).thenReturn(logsEntity);

        Page<LogDomain> logsDomain = logJdbcAdapter.findAllLog(pageable);

        assertEquals(logsDomain.getSize(), logsEntity.getSize());
        assertEquals(logsDomain.getTotalElements(), logsEntity.getTotalElements());
        assertEquals(logsDomain.getTotalPages(), logsEntity.getTotalPages());

        verify(logRepository, times(1)).findAll(pageable);
    }

    @Test
    void testSaveLogAsyncSuccess() throws Exception {
        LogEntity logEntity = mockFactoryDomain.getLogEntity();
        when(logRepository.save(any(LogEntity.class))).thenReturn(logEntity);

        logJdbcAdapter.saveLogAsync(logEntity.getEndpoint(), logEntity.getHttpMethod(), logEntity.getResponseBody());

        Thread.sleep(5);

        ArgumentCaptor<LogEntity> captor = ArgumentCaptor.forClass(LogEntity.class);
        verify(logRepository, times(1)).save(captor.capture());

        LogEntity savedLog = captor.getValue();
        assertEquals(logEntity.getEndpoint(), savedLog.getEndpoint());
        assertEquals(logEntity.getHttpMethod(), savedLog.getHttpMethod());
        assertEquals(logEntity.getResponseBody(), savedLog.getResponseBody());
    }
}
