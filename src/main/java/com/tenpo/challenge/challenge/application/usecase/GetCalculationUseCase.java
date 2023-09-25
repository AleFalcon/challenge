package com.tenpo.challenge.challenge.application.usecase;

import com.tenpo.challenge.challenge.application.exception.NotFoundException;
import com.tenpo.challenge.challenge.application.port.in.GetCalculationPort;
import com.tenpo.challenge.challenge.application.port.out.ConsultJdbc;
import com.tenpo.challenge.challenge.application.port.out.PercentageRest;
import com.tenpo.challenge.challenge.domain.CalculationResultDomain;
import com.tenpo.challenge.challenge.domain.PercentageDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class GetCalculationUseCase implements GetCalculationPort {
    private final PercentageRest percentageRest;
    private final ConsultJdbc consultJdbc;

    public GetCalculationUseCase(PercentageRest percentageRest, ConsultJdbc consultJdbc) {
        this.percentageRest = percentageRest;
        this.consultJdbc = consultJdbc;
    }

    @Override
    @Retryable(maxAttempts = 3, value = { NotFoundException.class }, backoff = @Backoff(delay = 1000))
    public CalculationResultDomain execute(Double number1, Double number2) {
        PercentageDomain percentageDomain = percentageRest.executeGetPercentage();
        if (percentageDomain.getPercentage() == null) {
            percentageDomain.setPercentage(consultJdbc.getLastConsult().getPercentage());
        }
        return consultJdbc.saveConsult(number1, number2, percentageDomain);
    }

    @Recover
    public CalculationResultDomain recover(NotFoundException e) {
        throw e;
    }
}
