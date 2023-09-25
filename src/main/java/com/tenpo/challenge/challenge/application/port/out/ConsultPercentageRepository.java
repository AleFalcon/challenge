package com.tenpo.challenge.challenge.application.port.out;

import com.tenpo.challenge.challenge.domain.ConsultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsultPercentageRepository extends JpaRepository<ConsultEntity, Long> {
    Optional<ConsultEntity> findFirstByOrderByCreatedAtDesc();
}
