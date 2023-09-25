package com.tenpo.challenge.challenge.adapter.rest;

import com.tenpo.challenge.challenge.application.port.out.PercentageRepository;
import com.tenpo.challenge.challenge.application.port.out.PercentageRest;
import com.tenpo.challenge.challenge.domain.PercentageDomain;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class PercentageRestAdapter implements PercentageRest {
    private final PercentageRepository percentageRepository;

    public PercentageRestAdapter(PercentageRepository percentageRepository) {
        this.percentageRepository = percentageRepository;
    }
    @Cacheable(value = "percentageCache", key = "#root.method.name")
    public PercentageDomain executeGetPercentage() {
        try {
            return PercentageDomain.toDomain(percentageRepository.getPercentage());
        } catch (Exception e) {
            return PercentageDomain.builder().build();
        }
    }
}
