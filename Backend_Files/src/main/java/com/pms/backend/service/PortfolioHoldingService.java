package com.pms.backend.service;

import java.util.List;

import com.pms.backend.entity.PortfolioHolding;

public interface PortfolioHoldingService {

    List<PortfolioHolding> getAllHoldings();

    List<PortfolioHolding> getHoldingsByPortfolioId(Long portfolioId);

    PortfolioHolding getHoldingById(Long id);

    PortfolioHolding createHolding(PortfolioHolding holding);

    PortfolioHolding updateHolding(Long id, PortfolioHolding holding);

    boolean deleteHolding(Long id);

    Double calculateTotalValue(Long portfolioId);
}