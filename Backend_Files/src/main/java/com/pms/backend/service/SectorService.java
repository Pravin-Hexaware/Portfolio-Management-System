
package com.pms.backend.service;

import com.pms.backend.entity.Sector;
import java.util.List;

public interface SectorService {
    List<Sector> getAllSectors();
    Sector createSector(Sector sector);
}