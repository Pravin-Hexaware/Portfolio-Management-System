package com.pms.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "investment_themes")
public class InvestmentTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "risk_level")
    private String riskLevel;

    @Column(name = "investment_horizon")
    private String investmentHorizon;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getInvestmentHorizon() {
		return investmentHorizon;
	}

	public void setInvestmentHorizon(String investmentHorizon) {
		this.investmentHorizon = investmentHorizon;
	}

	public InvestmentTheme(Long id, String name, String description, String riskLevel, String investmentHorizon) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.riskLevel = riskLevel;
		this.investmentHorizon = investmentHorizon;
	}

	public InvestmentTheme() {
		super();
	}
    
    
}
