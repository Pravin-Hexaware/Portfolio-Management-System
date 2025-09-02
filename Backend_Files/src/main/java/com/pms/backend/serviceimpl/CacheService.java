package com.pms.backend.serviceimpl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
	
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "assetClassEntities", allEntries = true)
    public void refreshassetClassEntitiesCache() {
        System.out.println("assetClassEntities class cache cleared.");
    }
    
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "assetClasses", allEntries = true)
    public void refreshassetClassesCache() {
        System.out.println("assetClasses class cache cleared.");
    }
    
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "countries", allEntries = true)
    public void refreshcountriesCache() {
        System.out.println("countries class cache cleared.");
    }
    
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "currency", allEntries = true)
    public void refreshcurrencyCache() {
        System.out.println("currency class cache cleared.");
    }
    
	
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "exchanges", allEntries = true)
    public void refreshexchangesCache() {
        System.out.println("exchanges class cache cleared.");
    }
    
	
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "industries", allEntries = true)
    public void refreshindustriesCache() {
        System.out.println("industries class cache cleared.");
    }
    
	
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "getallthemes", allEntries = true)
    public void refreshgetallthemesCache() {
        System.out.println("getallthemes class cache cleared.");
    }
    
	
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "portfolioholdings", allEntries = true)
    public void refreshportfolioholdingsCache() {
        System.out.println("portfolioholdings class cache cleared.");
    }
    
	
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "getallportfolios", allEntries = true)
    public void refreshgetallportfoliosCache() {
        System.out.println("getallportfolios class cache cleared.");
    }
    
	
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "sectors", allEntries = true)
    public void refreshsectorsCache() {
        System.out.println("sectors class cache cleared.");
    }
    
	
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "getallsecurities", allEntries = true)
    public void refreshgetallsecuritiesCache() {
        System.out.println("getallsecurities class cache cleared.");
    }
    
	
	@Scheduled(fixedRate = 600000)
    @CacheEvict(value = "themeallocation", allEntries = true)
    public void refreshthemeallocationCache() {
        System.out.println("themeallocation class cache cleared.");
    }
    
    
}