package com.tenpo.challenge.challenge.domain;

import com.tenpo.challenge.challenge.application.model.PercentageModel;
import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PercentageDomainTest {
    private MockFactoryDomain mockFactoryDomain;

    @BeforeEach
    void setUp() {
        mockFactoryDomain = new MockFactoryDomain();
    }

    @Test
    @DisplayName("Convert Percentage Model to Domain")
    void convertSuccessTest() {
        PercentageModel percentageModel = mockFactoryDomain.getPercentageModel();
        PercentageDomain percentageDomain = PercentageDomain.toDomain(percentageModel);

        Assertions.assertEquals(percentageDomain.getPercentage(), percentageModel.getPercentage());
    }
}
