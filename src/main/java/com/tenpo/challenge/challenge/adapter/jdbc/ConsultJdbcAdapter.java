package com.tenpo.challenge.challenge.adapter.jdbc;

import com.tenpo.challenge.challenge.application.exception.NotFoundException;
import com.tenpo.challenge.challenge.application.port.out.ConsultPercentageRepository;
import com.tenpo.challenge.challenge.application.port.out.ConsultJdbc;
import com.tenpo.challenge.challenge.domain.CalculationResultDomain;
import com.tenpo.challenge.challenge.domain.ConsultEntity;
import com.tenpo.challenge.challenge.domain.PercentageDomain;
import org.springframework.stereotype.Component;

@Component
public class ConsultJdbcAdapter implements ConsultJdbc {
    private final ConsultPercentageRepository consultPercentageRepository;
    private static final String ERROR_MESSAGE = "The percentage is not found";

    public ConsultJdbcAdapter(ConsultPercentageRepository consultPercentageRepository) {
        this.consultPercentageRepository = consultPercentageRepository;
    }

    @Override
    public CalculationResultDomain saveConsult(Double number1, Double number2, PercentageDomain percentageDomain) {
        ConsultEntity consultEntity = consultPercentageRepository.save(
                new ConsultEntity(number1, number2, percentageDomain.getPercentage()));
        return CalculationResultDomain.toDomain(consultEntity);
    }

    @Override
    public CalculationResultDomain getLastConsult(){
        ConsultEntity consultEntity = consultPercentageRepository
                .findFirstByOrderByCreatedAtDesc().orElseThrow(() -> new NotFoundException(ERROR_MESSAGE));
        return CalculationResultDomain.toDomain(consultEntity);
    }
}
