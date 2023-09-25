package com.tenpo.challenge.challenge.application.port.out;

import com.tenpo.challenge.challenge.domain.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Long>{
}
