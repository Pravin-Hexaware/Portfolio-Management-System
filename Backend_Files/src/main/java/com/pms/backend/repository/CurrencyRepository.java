package com.pms.backend.repository;

import com.pms.backend.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    boolean existsByCodeIgnoreCase(String code);
}