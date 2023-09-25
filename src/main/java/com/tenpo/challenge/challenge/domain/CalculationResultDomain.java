package com.tenpo.challenge.challenge.domain;

import lombok.Getter;

@Getter
public class CalculationResultDomain {
    private final Double percentage;
    private final Double result;

    public CalculationResultDomain(ConsultEntity consultEntity) {
        double sum = ( consultEntity.getFirstNumber() + consultEntity.getSecondNumber());
        this.result = sum * consultEntity.getPercentage() / 100 + sum;
        this.percentage = consultEntity.getPercentage();

    }

    public static CalculationResultDomain toDomain(ConsultEntity consultEntity){
        return new CalculationResultDomain(consultEntity);
    }
}
