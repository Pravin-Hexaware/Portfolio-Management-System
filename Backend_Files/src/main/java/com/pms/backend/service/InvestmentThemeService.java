package com.pms.backend.service;

import com.pms.backend.entity.InvestmentTheme;

import java.util.List;

public interface InvestmentThemeService {

    List<InvestmentTheme> getAllThemes();

    InvestmentTheme getThemeById(Long id);

    InvestmentTheme createTheme(InvestmentTheme theme);

    InvestmentTheme updateTheme(Long id, InvestmentTheme updatedTheme);

    void deleteTheme(Long id);

    List<InvestmentTheme> searchByName(String keyword);

    long countThemes();

    List<InvestmentTheme> filterByInvestmentHorizon(String horizon);

    void deleteThemesBulk(List<Long> ids);
}
