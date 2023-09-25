package com.tenpo.challenge.challenge.adapter.controller.model.out;

import com.tenpo.challenge.challenge.adapter.controller.model.out.CalculationResultResponse;
import com.tenpo.challenge.challenge.domain.CalculationResultDomain;
import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationResultResponseTest {
    private MockFactoryDomain mockFactoryDomain;

    @BeforeEach
    void setUp() {
        mockFactoryDomain = new MockFactoryDomain();
    }

    @Test
    void testConvertSuccess() {
        CalculationResultDomain calculationResultDomain = mockFactoryDomain.getCalculationResultDomain();
        CalculationResultResponse calculationResultResponse = CalculationResultResponse.fromDomain(calculationResultDomain);

        assertEquals(calculationResultDomain.getResult(), calculationResultResponse.getResult());
        assertEquals(calculationResultDomain.getPercentage(), calculationResultResponse.getPercentage());
    }
}
