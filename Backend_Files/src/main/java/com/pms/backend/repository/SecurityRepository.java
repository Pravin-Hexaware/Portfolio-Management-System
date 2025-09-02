
package com.pms.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pms.backend.entity.Security;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Long> {

    Optional<Security> findByIsin(String isin);

    Optional<Security> findBySymbol(String symbol);

    List<Security> findBySector(String sector);
    
    boolean existsByIsin(String isin);
    boolean existsBySymbol(String symbol);
    
    @Query("SELECT DISTINCT s.currency FROM Security s WHERE s.currency IS NOT NULL")
    List<String> findDistinctCurrencies();
 
    @Query("SELECT DISTINCT s.exchange FROM Security s WHERE s.exchange IS NOT NULL")
    List<String> findDistinctExchanges();
}
