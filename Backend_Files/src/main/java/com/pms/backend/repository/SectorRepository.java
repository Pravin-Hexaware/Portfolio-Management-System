package com.pms.backend.repository;

import com.pms.backend.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    boolean existsByName(String name);
}