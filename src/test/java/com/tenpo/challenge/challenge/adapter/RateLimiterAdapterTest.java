package com.tenpo.challenge.challenge.adapter;

import com.tenpo.challenge.challenge.domain.CalculationResultDomain;
import com.tenpo.challenge.challenge.domain.ConsultEntity;
import com.tenpo.challenge.challenge.domain.RateLimiter;
import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

class RateLimiterAdapterTest {
    private RateLimiter rateLimiter;
    private RateLimiterAdapter rateLimiterAdapter;

    @BeforeEach
    void setUp() {
        rateLimiter = Mockito.mock(RateLimiter.class);
        rateLimiterAdapter = new RateLimiterAdapter(rateLimiter);
    }

    @Test
    @DisplayName("Convert Consult Entity to Domain")
    void testIsAllowed() {
        when(rateLimiter.isAllowed()).thenReturn(true);
        Assertions.assertTrue(rateLimiterAdapter.isAllowed());
    }
}
