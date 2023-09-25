package com.tenpo.challenge.challenge.adapter.controller.rest;

import com.tenpo.challenge.challenge.adapter.rest.PercentageRestAdapter;
import com.tenpo.challenge.challenge.application.port.out.PercentageRepository;
import com.tenpo.challenge.challenge.domain.CalculationResultDomain;
import com.tenpo.challenge.challenge.domain.PercentageDomain;
import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import com.tenpo.challenge.challenge.application.model.PercentageModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PercentageRestAdapterTest {
    private PercentageRepository percentageRepository;
    private PercentageRestAdapter percentageRestAdapter;
    private MockFactoryDomain mockFactoryDomain;

    @BeforeEach
    void setUp() {
        percentageRepository = Mockito.mock(PercentageRepository.class);
        percentageRestAdapter = new PercentageRestAdapter(percentageRepository);
        mockFactoryDomain = new MockFactoryDomain();
    }

    @Test
    void testExecuteGetPercentageSuccess() {
        PercentageModel percentageModel = mockFactoryDomain.getPercentageModel();
        when(percentageRepository.getPercentage()).thenReturn(percentageModel);

        PercentageDomain result = percentageRestAdapter.executeGetPercentage();

        Assertions.assertEquals(result.getPercentage(), percentageModel.getPercentage());
    }

    @Test
    void testExecuteGetPercentageFail() {
        when(percentageRepository.getPercentage()).thenReturn(null);

        PercentageDomain result = percentageRestAdapter.executeGetPercentage();

        Assertions.assertNull(result.getPercentage());
    }
}
