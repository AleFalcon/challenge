package com.tenpo.challenge.challenge.application.exception;

import lombok.Getter;

@Getter
public class FeignGenericException extends RuntimeException {
    public FeignGenericException(String message) {
        super(message);
    }
}
