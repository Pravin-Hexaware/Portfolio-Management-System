package com.pms.backend.serviceimpl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pms.backend.entity.Sector;
import com.pms.backend.repository.SectorRepository;
import com.pms.backend.service.SectorService;

@Service
public class SectorServiceImpl implements SectorService {

    private final SectorRepository sectorRepository;
    private final CacheService cache;



    
    public SectorServiceImpl(SectorRepository sectorRepository, CacheService cache) {
		super();
		this.sectorRepository = sectorRepository;
		this.cache = cache;
	}

	@Cacheable("sectors")
    @Override
    public List<Sector> getAllSectors() {
    	 System.out.println("Fetching sector classes from DB...");
        return sectorRepository.findAll();
    }

    @Override
    public Sector createSector(Sector sector) {
        if (sectorRepository.existsByName(sector.getName())) {
            throw new IllegalArgumentException("Sector already exists");
        }
        Sector saved=sectorRepository.save(sector);
        cache.refreshsectorsCache();
        return saved;
    }
    

}