package com.tenpo.challenge.challenge.application.port.out;

import com.tenpo.challenge.challenge.domain.LogDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

public interface LogJdbc {

    @Async
    void saveLogAsync(String endpoint, String method, String responseBody);
    Page<LogDomain> findAllLog(Pageable pageable);
}
