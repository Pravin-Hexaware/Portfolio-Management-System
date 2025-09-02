package com.pms.backend.service;

import com.pms.backend.entity.Country;
import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();
    Country createCountry(Country country);
}