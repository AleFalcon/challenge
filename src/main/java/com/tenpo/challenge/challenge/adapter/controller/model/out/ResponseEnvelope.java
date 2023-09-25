package com.tenpo.challenge.challenge.adapter.controller.model.out;

import lombok.Builder;
import lombok.Data;

@Data
public class ResponseEnvelope<T> {
    private int status;
    private String message;
    private T data;

    public ResponseEnvelope(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
