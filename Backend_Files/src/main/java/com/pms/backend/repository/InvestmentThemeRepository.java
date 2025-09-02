package com.pms.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pms.backend.entity.InvestmentTheme;

@Repository
public interface InvestmentThemeRepository extends JpaRepository<InvestmentTheme, Long> {
	
	// Search by name (case-insensitive, partial match)
    List<InvestmentTheme> findByNameContainingIgnoreCase(String keyword);

    // Filter by investment horizon (case-insensitive exact match)
    List<InvestmentTheme> findByInvestmentHorizonIgnoreCase(String horizon);
}
