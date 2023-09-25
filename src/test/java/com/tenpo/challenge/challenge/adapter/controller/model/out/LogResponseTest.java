package com.tenpo.challenge.challenge.adapter.controller.model.out;

import com.tenpo.challenge.challenge.adapter.controller.model.out.LogResponse;
import com.tenpo.challenge.challenge.domain.LogDomain;
import com.tenpo.challenge.challenge.mock.MockFactoryDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogResponseTest {
    private MockFactoryDomain mockFactoryDomain;

    @BeforeEach
    void setUp() {
        mockFactoryDomain = new MockFactoryDomain();
    }

    @Test
    void testConvertSuccess() {
        LogDomain logDomain = mockFactoryDomain.getLogDomain();
        LogResponse logResponse = LogResponse.fromDomain(logDomain);

        assertEquals(logDomain.getHttpMethod(), logResponse.getHttpMethod());
        assertEquals(logDomain.getResponseBody(), logResponse.getResponseBody());
        assertEquals(logDomain.getEndpoint(), logResponse.getEndpoint());
    }
}
