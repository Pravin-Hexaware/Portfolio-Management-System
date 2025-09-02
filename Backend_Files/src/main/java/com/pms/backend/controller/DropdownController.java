package com.pms.backend.controller;
 
import org.springframework.web.bind.annotation.*;
 
import com.pms.backend.entity.Benchmark;
import com.pms.backend.entity.Rebalance;
import com.pms.backend.entity.Type;
import com.pms.backend.serviceimpl.DropdownService;
 
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/dropdown")
public class DropdownController {
 
    private final DropdownService dropdownService;
 
    public DropdownController(DropdownService dropdownService) {
        this.dropdownService = dropdownService;
    }
 
    @GetMapping("/benchmarks")
    public List<Benchmark> getBenchmarks() {
        return dropdownService.getBenchmarks();
    }
 
    @GetMapping("/types")
    public List<Type> getTypes() {
        return dropdownService.getTypes();
    }
 
    @GetMapping("/rebalances")
    public List<Rebalance> getRebalances() {
        return dropdownService.getRebalances();
    }
}