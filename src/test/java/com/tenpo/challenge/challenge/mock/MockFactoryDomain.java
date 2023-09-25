package com.tenpo.challenge.challenge.mock;

import com.tenpo.challenge.challenge.application.model.PercentageModel;
import com.tenpo.challenge.challenge.domain.CalculationResultDomain;
import com.tenpo.challenge.challenge.domain.ConsultEntity;
import com.tenpo.challenge.challenge.domain.LogDomain;
import com.tenpo.challenge.challenge.domain.LogEntity;
import com.tenpo.challenge.challenge.domain.PercentageDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class MockFactoryDomain {
    private static final Double FIRST_NUMBER = 5.1;
    private static final Double SECOND_NUMBER = 3.1;
    private static final Double PERCENTAGE = 10.0;
    private static final String ENDPOINT = "getResult";
    private static final String METHOD = "GET";
    private static final String RESPONSE_BODY = "ResponseEnvelope(status=200, message=OK, data=CalculationResultResponse(percentage=10.0, result=11.0))";
    public ConsultEntity getConsultEntity(){
        return new ConsultEntity(FIRST_NUMBER, SECOND_NUMBER, PERCENTAGE);
    }
    public LogEntity getLogEntity() {
        return new LogEntity(ENDPOINT, METHOD, RESPONSE_BODY);
    }
    public PercentageModel getPercentageModel() {
        return PercentageModel.builder().percentage(PERCENTAGE).build();
    }
    public PercentageDomain getPercentageDomain(){
        return PercentageDomain.toDomain(getPercentageModel());
    }
    public CalculationResultDomain getCalculationResultDomain() {
        return CalculationResultDomain.toDomain(getConsultEntity());
    }
    public Page<LogEntity> getPageLogEntity(){
        List<LogEntity> logEntities = new ArrayList<>();
        logEntities.add(getLogEntity());
        return new PageImpl<>(logEntities, PageRequest.of(1, 1), logEntities.size());
    }
    public Page<LogDomain> getPageLogDomain(){
        List<LogDomain> logsDomain = new ArrayList<>();
        logsDomain.add(getLogDomain());
        return new PageImpl<>(logsDomain, PageRequest.of(1, 1), logsDomain.size());
    }
    public LogDomain getLogDomain() {
        return LogDomain.toDomain(getLogEntity());
    }
}
