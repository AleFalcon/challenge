package com.tenpo.challenge.challenge.domain;

import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LogDomainTest {
    private MockFactoryDomain mockFactoryDomain;

    @BeforeEach
    void setUp() {
        mockFactoryDomain = new MockFactoryDomain();
    }

    @Test
    @DisplayName("Convert Log Entity to Domain")
    void convertSuccessTest() {
        LogEntity logEntity = mockFactoryDomain.getLogEntity();
        LogDomain logDomain = LogDomain.toDomain(logEntity);

        Assertions.assertEquals(logDomain.getEndpoint(), logEntity.getEndpoint());
        Assertions.assertEquals(logDomain.getResponseBody(), logEntity.getResponseBody());
        Assertions.assertEquals(logDomain.getHttpMethod(), logEntity.getHttpMethod());
    }
}
