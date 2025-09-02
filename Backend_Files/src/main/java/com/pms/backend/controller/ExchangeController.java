package com.pms.backend.controller;
import com.pms.backend.entity.Exchange;
import com.pms.backend.service.ExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/exchanges")
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public List<Exchange> getAllExchanges() {
        return exchangeService.getAllExchanges();
    }

    @PostMapping
    public ResponseEntity<?> createExchange(@RequestBody Exchange exchange) {
        try {
            Exchange saved = exchangeService.createExchange(exchange);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}