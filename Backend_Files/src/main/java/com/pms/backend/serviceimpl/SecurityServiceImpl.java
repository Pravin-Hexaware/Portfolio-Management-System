package com.pms.backend.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pms.backend.entity.Security;
import com.pms.backend.repository.SecurityRepository;
import com.pms.backend.service.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private SecurityRepository securityRepository;
    private final CacheService cache;
    
    public SecurityServiceImpl(SecurityRepository securityRepository, CacheService cache) {
		super();
		this.securityRepository = securityRepository;
		this.cache = cache;
	}

	@Cacheable("getallsecurities")
    @Override
    public List<Security> getAllSecurities() {
		System.out.println("Fetching securities classes from DB...");
        return securityRepository.findAll();
    }

    @Override
    public Security getSecurityById(Long id) {
        return securityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Security not found with ID: " + id));
    }

    @Override
    public Security getSecurityByIsin(String isin) {
        return securityRepository.findByIsin(isin)
                .orElseThrow(() -> new RuntimeException("Security not found with ISIN: " + isin));
    }

    @Override
    public Security getSecurityBySymbol(String symbol) {
        return securityRepository.findBySymbol(symbol)
                .orElseThrow(() -> new RuntimeException("Security not found with symbol: " + symbol));
    }

    @Override
    public List<Security> getSecuritiesBySector(String sector) {
        return securityRepository.findBySector(sector);
    }

    @Override
    public Security createSecurity(Security security) {
    	cache.refreshgetallsecuritiesCache();
        return securityRepository.save(security);
    }

    @Override
    public Security updateSecurity(Long id, Security updatedSecurity) {
        Security existing = getSecurityById(id);
        existing.setExchange(updatedSecurity.getExchange());
        existing.setSymbol(updatedSecurity.getSymbol());
        existing.setName(updatedSecurity.getName());
        existing.setIsin(updatedSecurity.getIsin());
        existing.setSector(updatedSecurity.getSector());
        existing.setIndustry(updatedSecurity.getIndustry());
        existing.setCurrency(updatedSecurity.getCurrency());
        existing.setCountry(updatedSecurity.getCountry());
        existing.setSecurityCode(updatedSecurity.getSecurityCode());
        existing.setAssetClass(updatedSecurity.getAssetClass());
        cache.refreshgetallsecuritiesCache();
        return securityRepository.save(existing);
    }

    @Override
    public void deleteSecurity(Long id) {
    	cache.refreshgetallsecuritiesCache();
        securityRepository.deleteById(id);
    }
    @Override
    public boolean existsByIsinOrSymbol(String isin, String symbol) {
        return securityRepository.existsByIsin(isin) || securityRepository.existsBySymbol(symbol);
    }
    
    @Override
    public List<String> getUniqueCurrencies() {
        return securityRepository.findDistinctCurrencies();
    }
 
    @Override
    public List<String> getUniqueExchanges() {
        return securityRepository.findDistinctExchanges();
    }
}
