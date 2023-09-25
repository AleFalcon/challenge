package com.tenpo.challenge.challenge.adapter.jdbc;

import com.tenpo.challenge.challenge.application.port.out.LogJdbc;
import com.tenpo.challenge.challenge.application.port.out.LogRepository;
import com.tenpo.challenge.challenge.domain.LogDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import com.tenpo.challenge.challenge.domain.LogEntity;
import org.springframework.stereotype.Component;

@Component
public class LogJdbcAdapter implements LogJdbc {
    private final LogRepository logRepository;

    public LogJdbcAdapter(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Async
    @Override
    public void saveLogAsync(String endpoint, String method, String responseBody) {
        LogEntity logEntry = new LogEntity(endpoint, method, responseBody);
        logRepository.save(logEntry);
    }

    @Override
    public Page<LogDomain> findAllLog(Pageable pageable){
        Page<LogEntity> logsEntity = logRepository.findAll(pageable);
        return logsEntity.map(LogDomain::toDomain);

    }
}
