package com.pms.backend.repository;

import com.pms.backend.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    boolean existsByNameIgnoreCase(String name);
}