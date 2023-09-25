package com.tenpo.challenge.challenge.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.challenge.challenge.application.exception.BadRequestException;
import com.tenpo.challenge.challenge.application.exception.FeignGenericException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.util.Map;

public class ErrorHandlerDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        String message;
        try {
            message = new ObjectMapper()
                    .readValue(response.body().asInputStream(), Map.class)
                    .get("message").toString();
        } catch (IOException e) {
            message = "The request is badly formatted";
        }

        return (response.status() == 400) ?
                new BadRequestException(message) :
                new FeignGenericException("Feign Failed");
    }
}
