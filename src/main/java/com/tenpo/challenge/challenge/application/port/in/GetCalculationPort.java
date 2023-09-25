package com.tenpo.challenge.challenge.application.port.in;

import com.tenpo.challenge.challenge.domain.CalculationResultDomain;

public interface GetCalculationPort {
    CalculationResultDomain execute(Double number1, Double number2);
}
