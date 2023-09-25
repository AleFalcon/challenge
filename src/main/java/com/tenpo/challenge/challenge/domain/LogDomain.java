package com.tenpo.challenge.challenge.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class LogDomain {
    private String endpoint;
    private String httpMethod;
    private String responseBody;

    public static LogDomain toDomain(LogEntity logEntity) {
        return LogDomain.builder()
                .endpoint(logEntity.getEndpoint())
                .httpMethod(logEntity.getHttpMethod())
                .responseBody(logEntity.getResponseBody())
                .build();
    }
}
