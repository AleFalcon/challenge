package com.tenpo.challenge.challenge.adapter.controller.model.out;

import com.tenpo.challenge.challenge.domain.CalculationResultDomain;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculationResultResponse {
    private Double percentage;
    private Double result;

    public static CalculationResultResponse fromDomain(CalculationResultDomain calculationResultDomain) {
        return CalculationResultResponse.builder()
                .result(calculationResultDomain.getResult())
                .percentage(calculationResultDomain.getPercentage())
                .build();
    }
}
