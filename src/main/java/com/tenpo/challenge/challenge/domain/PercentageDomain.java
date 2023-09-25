package com.tenpo.challenge.challenge.domain;

import com.tenpo.challenge.challenge.application.model.PercentageModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PercentageDomain {
    private Double percentage;

    public static PercentageDomain toDomain(PercentageModel percentageModel) {
        return PercentageDomain.builder()
                .percentage(percentageModel.getPercentage())
                .build();
    }
}
