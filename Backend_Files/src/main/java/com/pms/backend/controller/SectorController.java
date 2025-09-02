package com.pms.backend.controller;

import com.pms.backend.entity.Sector;
import com.pms.backend.service.SectorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/sectors")
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping
    public List<Sector> getAllSectors() {
        return sectorService.getAllSectors();
    }

    @PostMapping
    public Sector createSector(@RequestBody Sector sector) {
        return sectorService.createSector(sector);
    }
}