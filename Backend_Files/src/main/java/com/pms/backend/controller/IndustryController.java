package com.pms.backend.controller;
import com.pms.backend.entity.Industry;
import com.pms.backend.service.IndustryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/industries")
public class IndustryController {

    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @GetMapping
    public List<Industry> getAllIndustries() {
        return industryService.getAllIndustries();
    }

    @PostMapping
    public ResponseEntity<?> createIndustry(@RequestBody Industry industry) {
        try {
            Industry saved = industryService.createIndustry(industry);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}