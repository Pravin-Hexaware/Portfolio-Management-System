package com.pms.backend.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pms.backend.entity.InvestmentTheme;
import com.pms.backend.repository.InvestmentThemeRepository;
import com.pms.backend.service.InvestmentThemeService;

@Service
public class InvestmentThemeServiceImpl implements InvestmentThemeService {

    @Autowired
    private InvestmentThemeRepository themeRepository;
    private final CacheService cache;

    public InvestmentThemeServiceImpl(InvestmentThemeRepository themeRepository, CacheService cache) {
		super();
		this.themeRepository = themeRepository;
		this.cache = cache;
	}

	@Cacheable("getallthemes")
    @Override
    public List<InvestmentTheme> getAllThemes() {
        return themeRepository.findAll();
    }

    @Override
    public InvestmentTheme getThemeById(Long id) {
        return themeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investment Theme not found with id: " + id));
    }

    @Override
    public InvestmentTheme createTheme(InvestmentTheme theme) {
    	InvestmentTheme saved=themeRepository.save(theme);
    	cache.refreshgetallthemesCache();
    	return saved;
    	
    }

    @Override
    public InvestmentTheme updateTheme(Long id, InvestmentTheme updatedTheme) {
        InvestmentTheme existing = getThemeById(id);
        existing.setName(updatedTheme.getName());
        existing.setDescription(updatedTheme.getDescription());
        existing.setRiskLevel(updatedTheme.getRiskLevel());
        existing.setInvestmentHorizon(updatedTheme.getInvestmentHorizon());
        cache.refreshgetallthemesCache();
        return themeRepository.save(existing);
    }

    @Override
    public void deleteTheme(Long id) {
    	cache.refreshgetallthemesCache();
        themeRepository.deleteById(id);
    }

    @Override
    public List<InvestmentTheme> searchByName(String keyword) {
        return themeRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public long countThemes() {
        return themeRepository.count();
    }

    @Override
    public List<InvestmentTheme> filterByInvestmentHorizon(String horizon) {
        return themeRepository.findByInvestmentHorizonIgnoreCase(horizon);
    }

    @Override
    public void deleteThemesBulk(List<Long> ids) {
    	cache.refreshgetallthemesCache();
        themeRepository.deleteAllById(ids);
    }
}
