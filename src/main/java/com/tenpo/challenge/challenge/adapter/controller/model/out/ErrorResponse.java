package com.tenpo.challenge.challenge.adapter.controller.model.out;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
