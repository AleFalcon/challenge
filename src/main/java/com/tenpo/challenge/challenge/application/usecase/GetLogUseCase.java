package com.tenpo.challenge.challenge.application.usecase;

import com.tenpo.challenge.challenge.application.port.in.GetLogPort;
import com.tenpo.challenge.challenge.application.port.out.LogJdbc;
import com.tenpo.challenge.challenge.domain.LogDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetLogUseCase implements GetLogPort {
    private final LogJdbc logJdbc;

    public GetLogUseCase(LogJdbc logJdbc) {
        this.logJdbc = logJdbc;
    }

    @Override
    public Page<LogDomain> execute(Pageable pageable) {
        return logJdbc.findAllLog(pageable);
    }
}
