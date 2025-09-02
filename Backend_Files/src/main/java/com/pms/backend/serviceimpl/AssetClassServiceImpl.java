package com.pms.backend.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pms.backend.DTO.AssetClassDTO;
import com.pms.backend.entity.AssetClass;
import com.pms.backend.repository.AssetClassRepository;
import com.pms.backend.service.AssetClassService;

@Service
public class AssetClassServiceImpl implements AssetClassService {

    @Autowired
    private AssetClassRepository repository;
    private final CacheService cache;
    
    

    public AssetClassServiceImpl(AssetClassRepository repository, CacheService cache) {
		super();
		this.repository = repository;
		this.cache = cache;
	}
    
    @Cacheable("assetClassEntities")
	@Override
    public List<AssetClass> getAll() {
    	 System.out.println("Fetching asset classes from DB...");
        return repository.findAll();
    }


    @Override
    public AssetClass getById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    @Override
    public AssetClass save(AssetClass assetClass) {
        AssetClass saved = repository.save(assetClass);
        cache.refreshassetClassEntitiesCache();
        cache.refreshassetClassesCache();// Clear cache after update
        return saved;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        cache.refreshassetClassEntitiesCache();
        cache.refreshassetClassesCache();// Clear cache after delete
    }
    
    
    @Cacheable("assetClasses")
    @Override
    public List<AssetClassDTO> getAllAssetClassDTOs() {
        System.out.println("Fetching asset classes from DB...");
        return repository.findAll().stream()
            .map(a -> new AssetClassDTO(a.getId(), a.getClassName(), a.getSubClassName()))
            .collect(Collectors.toList());
    }
    
    
    


}