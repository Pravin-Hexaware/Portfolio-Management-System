package com.pms.backend.serviceimpl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pms.backend.entity.Country;
import com.pms.backend.repository.CountryRepository;
import com.pms.backend.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CacheService cache;

    
    public CountryServiceImpl(CountryRepository countryRepository, CacheService cache) {
		super();
		this.countryRepository = countryRepository;
		this.cache = cache;
	}

	@Cacheable("countries")
    @Override
    public List<Country> getAllCountries() {
    	 System.out.println("Fetching country classes from DB...");
        return countryRepository.findAll();
    }

    @Override
    public Country createCountry(Country country) {
        if (countryRepository.existsByNameIgnoreCase(country.getName())) {
            throw new IllegalArgumentException("Country already exists: " + country.getName());
        }
        Country saved=countryRepository.save(country);
        cache.refreshcountriesCache();
        return saved;
    }
    

}