package com.pms.backend.repository;

import com.pms.backend.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryRepository extends JpaRepository<Industry, Long> {
    boolean existsByNameIgnoreCase(String name);
}