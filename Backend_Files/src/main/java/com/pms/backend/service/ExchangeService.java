package com.pms.backend.service;
import com.pms.backend.entity.Exchange;
import java.util.List;

public interface ExchangeService {
    List<Exchange> getAllExchanges();
    Exchange createExchange(Exchange exchange);
}