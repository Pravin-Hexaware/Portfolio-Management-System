package com.pms.backend.service;

import com.pms.backend.entity.Security;

import java.util.List;

public interface SecurityService {

    List<Security> getAllSecurities();

    Security getSecurityById(Long id);

    Security getSecurityByIsin(String isin);

    Security getSecurityBySymbol(String symbol);

    List<Security> getSecuritiesBySector(String sector);

    Security createSecurity(Security security);

    Security updateSecurity(Long id, Security updatedSecurity);

    void deleteSecurity(Long id);
    
    public boolean existsByIsinOrSymbol(String isin, String symbol);
    
    List<String> getUniqueCurrencies();
    List<String> getUniqueExchanges();

}