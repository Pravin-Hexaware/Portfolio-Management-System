package com.pms.backend.service;

import java.util.List;

import com.pms.backend.entity.ThemeAllocation;

public interface ThemeAllocationService {

    List<ThemeAllocation> getAllAllocations();

    List<ThemeAllocation> getAllocationsByThemeId(Long themeId);

    ThemeAllocation getAllocationById(Long id);

    ThemeAllocation createAllocation(ThemeAllocation allocation);

    ThemeAllocation updateAllocation(Long id, ThemeAllocation updatedAllocation);

    void deleteAllocation(Long id);
}
