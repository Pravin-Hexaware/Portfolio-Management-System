package com.pms.backend.serviceimpl;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pms.backend.entity.Currency;
import com.pms.backend.repository.CurrencyRepository;
import com.pms.backend.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CacheService cache;


    
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CacheService cache) {
		super();
		this.currencyRepository = currencyRepository;
		this.cache = cache;
	}

	@Cacheable("currency")
    @Override
    public List<Currency> getAllCurrencies() {
    	 System.out.println("Fetching currency classes from DB...");
        return currencyRepository.findAll();
    }

    @Override
    public Currency createCurrency(Currency currency) {
        if (currencyRepository.existsByCodeIgnoreCase(currency.getCode())) {
            throw new IllegalArgumentException("Currency already exists: " + currency.getCode());
        }
        Currency saved=currencyRepository.save(currency);
        cache.refreshcurrencyCache();
        return saved;
    }
    

}