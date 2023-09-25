package com.tenpo.challenge.challenge.domain;

import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculationResultDomainTest {
    private MockFactoryDomain mockFactoryDomain;

    @BeforeEach
    void setUp() {
        mockFactoryDomain = new MockFactoryDomain();
    }

    @Test
    @DisplayName("Convert Consult Entity to Domain")
    void convertSuccessTest() {
        ConsultEntity consultEntity = mockFactoryDomain.getConsultEntity();
        double sum = ( consultEntity.getFirstNumber() + consultEntity.getSecondNumber());
        double result = sum * consultEntity.getPercentage() / 100 + sum;
        CalculationResultDomain calculationResultDomain = CalculationResultDomain.toDomain(consultEntity);

        Assertions.assertEquals(calculationResultDomain.getResult(), result);
        Assertions.assertEquals(calculationResultDomain.getPercentage(), consultEntity.getPercentage());
    }
}
