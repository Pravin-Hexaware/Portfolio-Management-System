package com.pms.backend.controller;
 
import java.util.List;

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
import com.pms.backend.DTO.AssetClassDTO;
import com.pms.backend.entity.AssetClass;
import com.pms.backend.service.AssetClassService;
 
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/asset-classes")
public class AssetClassController {
 
    @Autowired
    private AssetClassService service;
 
    @GetMapping
    public List<AssetClass> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AssetClass getById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @GetMapping("/dto")
    public List<AssetClassDTO> getAssetClassDTOs() {
        return service.getAllAssetClassDTOs();
    }
 
    @PostMapping
    public AssetClass create(@RequestBody AssetClass assetClass) {
        return service.save(assetClass);
    }
 
    @PutMapping("/{id}")
    public AssetClass update(@PathVariable Long id, @RequestBody AssetClass updatedAsset) {
        AssetClass existing = service.getById(id);
        if (existing != null) {
            updatedAsset.setId(id);
            return service.save(updatedAsset);
        }
        return null;
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}