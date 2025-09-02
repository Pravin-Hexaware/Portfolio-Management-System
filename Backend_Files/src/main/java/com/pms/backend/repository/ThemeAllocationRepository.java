package com.pms.backend.repository;
import com.pms.backend.entity.ThemeAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeAllocationRepository extends JpaRepository<ThemeAllocation, Long> {

    // 🔍 Fetch all allocations for a given theme
    List<ThemeAllocation> findByThemeId(Long themeId);
}
