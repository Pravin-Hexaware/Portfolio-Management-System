package com.pms.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pms.backend.entity.PortfolioHolding;

@Repository
public interface PortfolioHoldingRepository extends JpaRepository<PortfolioHolding, Long> {

    // Fetch all holdings for a specific portfolio
    List<PortfolioHolding> findByPortfolioId(Long portfolioId);

    // Optional: Fetch holdings by security ID if needed
    List<PortfolioHolding> findBySecurityId(Long securityId);

    // Optional: Fetch holdings by equity category
    List<PortfolioHolding> findByEquityCategory(String equityCategory);
}
