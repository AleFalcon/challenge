package com.tenpo.challenge.challenge.application.port.in;

import com.tenpo.challenge.challenge.domain.LogDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetLogPort {
    Page<LogDomain> execute(Pageable pageable);
}
