package com.pms.backend.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.backend.entity.ThemeAllocation;
import com.pms.backend.repository.ThemeAllocationRepository;
@CrossOrigin(origins = "http://localhost:4200") // Angular frontend
@RestController
@RequestMapping("/api/theme-allocations")
public class ThemeAllocationController {

    @Autowired
    private ThemeAllocationRepository themeAllocationRepository;

    // 🔹 Get all allocations
    @GetMapping
    public List<ThemeAllocation> getAllAllocations() {
        return themeAllocationRepository.findAll();
    }

    // 🔹 Get allocations by theme ID
    @GetMapping("/theme/{themeId}")
    public List<ThemeAllocation> getAllocationsByTheme(@PathVariable Long themeId) {
        return themeAllocationRepository.findByThemeId(themeId);
    }

    // 🔹 Create a new allocation
    @PostMapping
    public ThemeAllocation createAllocation(@RequestBody ThemeAllocation allocation) {
        return themeAllocationRepository.save(allocation);
    }

    // 🔹 Update an existing allocation
    @PutMapping("/{id}")
    public ThemeAllocation updateAllocation(@PathVariable Long id, @RequestBody ThemeAllocation updatedAllocation) {
        Optional<ThemeAllocation> optionalAllocation = themeAllocationRepository.findById(id);
        if (optionalAllocation.isPresent()) {
            ThemeAllocation allocation = optionalAllocation.get();
            allocation.setTheme(updatedAllocation.getTheme());
            allocation.setAssetClass(updatedAllocation.getAssetClass());
            allocation.setAllocationPercent(updatedAllocation.getAllocationPercent());
            return themeAllocationRepository.save(allocation);
        } else {
            throw new RuntimeException("Allocation not found with ID: " + id);
        }
    }

    // 🔹 Delete an allocation
    @DeleteMapping("/{id}")
    public void deleteAllocation(@PathVariable Long id) {
        themeAllocationRepository.deleteById(id);
    }
}

