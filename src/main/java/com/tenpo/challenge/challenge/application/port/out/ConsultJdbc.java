package com.tenpo.challenge.challenge.application.port.out;

import com.tenpo.challenge.challenge.domain.CalculationResultDomain;
import com.tenpo.challenge.challenge.domain.PercentageDomain;

public interface ConsultJdbc {
    CalculationResultDomain saveConsult(Double number1, Double number2, PercentageDomain percentageDomain);
    CalculationResultDomain getLastConsult();
}
