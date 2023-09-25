package com.tenpo.challenge.challenge.application.port.out;

import com.tenpo.challenge.challenge.application.model.PercentageModel;
import com.tenpo.challenge.challenge.config.ErrorHandlerDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ms-percentage", url = "${msPercentage.url}", configuration = {ErrorHandlerDecoder.class})
public interface PercentageRepository {
    @GetMapping("percentage")
    PercentageModel getPercentage();
}
