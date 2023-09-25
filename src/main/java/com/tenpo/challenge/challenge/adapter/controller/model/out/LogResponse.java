package com.tenpo.challenge.challenge.adapter.controller.model.out;

import com.tenpo.challenge.challenge.domain.LogDomain;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class LogResponse {
    private String endpoint;
    private String httpMethod;
    private String responseBody;

    public static LogResponse fromDomain(LogDomain logDomain) {
        return LogResponse.builder()
                .endpoint(logDomain.getEndpoint())
                .httpMethod(logDomain.getHttpMethod())
                .responseBody(logDomain.getResponseBody())
                .build();
    }
}
