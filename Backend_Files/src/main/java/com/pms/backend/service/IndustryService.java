package com.pms.backend.service;

import com.pms.backend.entity.Industry;
import java.util.List;

public interface IndustryService {
    List<Industry> getAllIndustries();
    Industry createIndustry(Industry industry);
}