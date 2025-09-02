package com.pms.backend.serviceimpl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pms.backend.entity.Industry;
import com.pms.backend.repository.IndustryRepository;
import com.pms.backend.service.IndustryService;

@Service
public class IndustryServiceImpl implements IndustryService {

    private final IndustryRepository industryRepository;
    private final CacheService cache;


    
    public IndustryServiceImpl(IndustryRepository industryRepository, CacheService cache) {
		super();
		this.industryRepository = industryRepository;
		this.cache = cache;
	}

	@Cacheable("industries")
    @Override
    public List<Industry> getAllIndustries() {
    	 System.out.println("Fetching industry classes from DB...");
        return industryRepository.findAll();
    }

    @Override
    public Industry createIndustry(Industry industry) {
        if (industryRepository.existsByNameIgnoreCase(industry.getName())) {
            throw new IllegalArgumentException("Industry already exists: " + industry.getName());
        }
        Industry saved=industryRepository.save(industry);
        cache.refreshindustriesCache();
        return saved;
    }
    
}