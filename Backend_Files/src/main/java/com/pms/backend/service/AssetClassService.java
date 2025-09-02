package com.pms.backend.service;

import java.util.List;

import com.pms.backend.DTO.AssetClassDTO;
import com.pms.backend.entity.AssetClass;

public interface AssetClassService {

    List<AssetClass> getAll();

    AssetClass getById(Long id);

    AssetClass save(AssetClass assetClass);

    void delete(Long id);
    
    List<AssetClassDTO> getAllAssetClassDTOs();

}