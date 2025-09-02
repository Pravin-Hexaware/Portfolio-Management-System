package com.pms.backend.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pms.backend.entity.Portfolio;
import com.pms.backend.repository.InvestmentThemeRepository;
import com.pms.backend.repository.PortfolioRepository;
import com.pms.backend.service.PortfolioService;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final InvestmentThemeRepository themeRepository;
    private final CacheService cache;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository,
                                InvestmentThemeRepository themeRepository,
                                CacheService cache) {
        this.portfolioRepository = portfolioRepository;
        this.themeRepository = themeRepository;
        this.cache = cache;
    }

    @Cacheable("getallportfolios")
    @Override
    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    @Override
    public Optional<Portfolio> getPortfolioById(Long id) {
        return portfolioRepository.findById(id);
    }

    @Override
    public Portfolio createPortfolio(Portfolio portfolio) {
        cache.refreshgetallportfoliosCache();
        return portfolioRepository.save(portfolio);
    }

    @Override
    public Portfolio updatePortfolio(Long id, Portfolio updated) {
        return portfolioRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setType(updated.getType());
            existing.setCurrency(updated.getCurrency());
            existing.setBenchmark(updated.getBenchmark());
            existing.setExchange(updated.getExchange());
            existing.setInitialInvestment(updated.getInitialInvestment());
            existing.setCurrentValue(updated.getCurrentValue());
            existing.setRebalancingFrequency(updated.getRebalancingFrequency());
            existing.setTheme(updated.getTheme());
            existing.setStatus(updated.getStatus());
            cache.refreshportfolioholdingsCache();
            return portfolioRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Portfolio not found"));
    }

    @Override
    public Portfolio updatePortfolioStatus(Long id, String status) {
        return portfolioRepository.findById(id).map(p -> {
            p.setStatus(status);
            return portfolioRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Portfolio not found"));
    }

    @Override
    public void deletePortfolio(Long id) {
        portfolioRepository.deleteById(id);
        cache.refreshportfolioholdingsCache();
        cache.refreshgetallportfoliosCache();
    }
}