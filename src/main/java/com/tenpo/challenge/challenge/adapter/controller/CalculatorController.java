package com.tenpo.challenge.challenge.adapter.controller;

import com.tenpo.challenge.challenge.adapter.controller.model.out.CalculationResultResponse;
import com.tenpo.challenge.challenge.adapter.controller.model.out.LogResponse;
import com.tenpo.challenge.challenge.adapter.controller.model.out.ResponseEnvelope;
import com.tenpo.challenge.challenge.application.exception.BadRequestException;
import com.tenpo.challenge.challenge.application.port.in.GetCalculationPort;
import com.tenpo.challenge.challenge.application.port.in.GetLogPort;
import com.tenpo.challenge.challenge.domain.LogDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {
    private final GetCalculationPort getCalculationPort;
    private final GetLogPort getLogPort;

    public CalculatorController(GetCalculationPort getCalculationPort, GetLogPort getLogPort) {
        this.getCalculationPort = getCalculationPort;
        this.getLogPort = getLogPort;
    }

    @GetMapping
    public ResponseEnvelope<CalculationResultResponse> getResult(
            @RequestParam(value = "number1") String number1,
            @RequestParam(value = "number2") String number2) {

        Double parsedNumber1 = validateAndParseNumber(number1);
        Double parsedNumber2 = validateAndParseNumber(number2);

        CalculationResultResponse result = CalculationResultResponse.fromDomain(
                getCalculationPort.execute(parsedNumber1, parsedNumber2));

        return new ResponseEnvelope<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), result);
    }

    @GetMapping("/logs")
    public ResponseEnvelope<Page<LogResponse>> getLogs(Pageable pageable) {
        Page<LogDomain> logsDomain = getLogPort.execute(pageable);
        return new ResponseEnvelope<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                logsDomain.map(LogResponse::fromDomain));
    }

    private Double validateAndParseNumber(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid number format: " + number);
        }
    }
}
