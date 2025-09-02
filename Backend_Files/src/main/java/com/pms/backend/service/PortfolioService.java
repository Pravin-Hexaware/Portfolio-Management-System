package com.pms.backend.service;

import com.pms.backend.entity.Portfolio;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {
    List<Portfolio> getAllPortfolios();
    Optional<Portfolio> getPortfolioById(Long id);
    Portfolio createPortfolio(Portfolio portfolio);
    Portfolio updatePortfolio(Long id, Portfolio portfolio);
    Portfolio updatePortfolioStatus(Long id, String status);
    void deletePortfolio(Long id);
}
