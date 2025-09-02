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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pms.backend.entity.InvestmentTheme;
import com.pms.backend.service.InvestmentThemeService;
@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/api/investment-themes")
public class InvestmentThemeController {

    @Autowired
    private InvestmentThemeService themeService;

    // GET all themes
    @GetMapping
    public ResponseEntity<List<InvestmentTheme>> getAllThemes() {
        List<InvestmentTheme> themes = themeService.getAllThemes();
        return ResponseEntity.ok(themes);
    }

    // GET theme by ID
    @GetMapping("/{id}")
    public ResponseEntity<InvestmentTheme> getThemeById(@PathVariable Long id) {
        InvestmentTheme theme = themeService.getThemeById(id);
        return ResponseEntity.ok(theme);
    }

    // POST - Create new theme
    @PostMapping
    public ResponseEntity<InvestmentTheme> createTheme(@RequestBody InvestmentTheme theme) {
        InvestmentTheme created = themeService.createTheme(theme);
        return ResponseEntity.ok(created);
    }

    // PUT - Update theme
    @PutMapping("/{id}")
    public ResponseEntity<InvestmentTheme> updateTheme(@PathVariable Long id, @RequestBody InvestmentTheme updatedTheme) {
        InvestmentTheme updated = themeService.updateTheme(id, updatedTheme);
        return ResponseEntity.ok(updated);
    }

    // DELETE - Delete theme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheme(@PathVariable Long id) {
        themeService.deleteTheme(id);
        return ResponseEntity.noContent().build();
    }
    
 // GET - Search themes by name (partial match)
    @GetMapping("/search")
    public ResponseEntity<List<InvestmentTheme>> searchThemesByName(@RequestParam String keyword) {
        List<InvestmentTheme> results = themeService.searchByName(keyword);
        return ResponseEntity.ok(results);
    }

    // GET - Count total themes
    @GetMapping("/count")
    public ResponseEntity<Long> countThemes() {
        long count = themeService.countThemes();
        return ResponseEntity.ok(count);
    }

    // GET - Filter themes by investment horizon
    @GetMapping("/filter")
    public ResponseEntity<List<InvestmentTheme>> filterByHorizon(@RequestParam String horizon) {
        List<InvestmentTheme> filtered = themeService.filterByInvestmentHorizon(horizon);
        return ResponseEntity.ok(filtered);
    }

    // DELETE - Bulk delete by IDs
    @DeleteMapping("/bulk-delete")
    public ResponseEntity<Void> deleteThemesBulk(@RequestBody List<Long> ids) {
        themeService.deleteThemesBulk(ids);
        return ResponseEntity.noContent().build();
    }

}
