package com.pms.backend.serviceimpl;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pms.backend.entity.Exchange;
import com.pms.backend.repository.ExchangeRepository;
import com.pms.backend.service.ExchangeService;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final CacheService cache;
    
    
    public ExchangeServiceImpl(ExchangeRepository exchangeRepository, CacheService cache) {
		super();
		this.exchangeRepository = exchangeRepository;
		this.cache = cache;
	}

	@Cacheable("exchanges")
    @Override
    public List<Exchange> getAllExchanges() {
    	 System.out.println("Fetching exchange classes from DB...");
        return exchangeRepository.findAll();
    }

    @Override
    public Exchange createExchange(Exchange exchange) {
        if (exchangeRepository.existsByNameIgnoreCase(exchange.getName())) {
            throw new IllegalArgumentException("Exchange already exists: " + exchange.getName());
        }
        Exchange saved=exchangeRepository.save(exchange);
        cache.refreshexchangesCache();
        
        return saved;
    }
    

}