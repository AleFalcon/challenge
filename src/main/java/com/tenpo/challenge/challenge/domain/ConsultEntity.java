package com.tenpo.challenge.challenge.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Getter
public class ConsultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double firstNumber;
    private Double secondNumber;
    private Double percentage;
    private LocalDateTime createdAt;

    public ConsultEntity(Double firstNumber, Double secondNumber, Double percentage) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.percentage = percentage;
        this.createdAt = LocalDateTime.now();
    }

    public ConsultEntity() { }
}
