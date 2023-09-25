package com.tenpo.challenge.challenge.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Configuration
public class WireMockConfig {
    private WireMockServer wireMockServer = new WireMockServer(8089);

    @PostConstruct
    public void startWireMock() {
        wireMockServer.start();
        wireMockServer.stubFor(get(urlEqualTo("/percentage"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("{\"percentage\": 15.00}")));
    }

    @PreDestroy
    public void stopWireMock() {
        wireMockServer.stop();
    }

    @Bean
    public WireMockServer wireMockServer() {
        return wireMockServer;
    }
}

