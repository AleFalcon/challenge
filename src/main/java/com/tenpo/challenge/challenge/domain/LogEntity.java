package com.tenpo.challenge.challenge.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@Getter
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String endpoint;
    private String httpMethod;
    private String responseBody;

    public LogEntity(String endpoint, String httpMethod, String responseBody) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
        this.responseBody = responseBody;
    }

    public LogEntity() { }
}
