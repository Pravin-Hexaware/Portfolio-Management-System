package com.pms.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pms.backend.entity.PortfolioHolding;
import com.pms.backend.service.PortfolioHoldingService;
@CrossOrigin(origins = "http://localhost:4200") // Angular frontend
@RestController
@RequestMapping("/api/holdings")
public class PortfolioHoldingController {

    @Autowired
    private PortfolioHoldingService holdingService;

    // 🔍 Get all holdings
    @GetMapping
    public ResponseEntity<List<PortfolioHolding>> getAllHoldings() {
        return ResponseEntity.ok(holdingService.getAllHoldings());
    }

    // 🔍 Get holdings by portfolio ID
    @GetMapping("/portfolio/{portfolioId}")
    public ResponseEntity<List<PortfolioHolding>> getHoldingsByPortfolio(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(holdingService.getHoldingsByPortfolioId(portfolioId));
    }

    // 🔍 Get a specific holding by ID
    @GetMapping("/{id}")
    public ResponseEntity<PortfolioHolding> getHoldingById(@PathVariable Long id) {
        PortfolioHolding holding = holdingService.getHoldingById(id);
        return holding != null ? ResponseEntity.ok(holding) : ResponseEntity.notFound().build();
    }

    // ➕ Add a new holding
    @PostMapping
    public ResponseEntity<PortfolioHolding> createHolding(@RequestBody PortfolioHolding holding) {
        PortfolioHolding created = holdingService.createHolding(holding);
        return ResponseEntity.ok(created);
    }

    // 🔄 Update an existing holding
    @PutMapping("/{id}")
    public ResponseEntity<PortfolioHolding> updateHolding(@PathVariable Long id, @RequestBody PortfolioHolding holding) {
        PortfolioHolding updated = holdingService.updateHolding(id, holding);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // ❌ Delete a holding
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHolding(@PathVariable Long id) {
        boolean deleted = holdingService.deleteHolding(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    // 📊 Get total value of holdings in a portfolio
    @GetMapping("/portfolio/{portfolioId}/value")
    public ResponseEntity<?> getPortfolioHoldingsValue(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(holdingService.calculateTotalValue(portfolioId));
    }
}