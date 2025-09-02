package com.pms.backend.service;

import com.pms.backend.entity.Currency;
import java.util.List;

public interface CurrencyService {
    List<Currency> getAllCurrencies();
    Currency createCurrency(Currency currency);
}