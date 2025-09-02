package com.pms.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "theme_allocations")
public class ThemeAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private InvestmentTheme theme;

    @ManyToOne
    @JoinColumn(name = "asset_class_id")
    private AssetClass assetClass;

    @Column(name = "allocation_percent")
    private Double allocationPercent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InvestmentTheme getTheme() {
		return theme;
	}

	public void setTheme(InvestmentTheme theme) {
		this.theme = theme;
	}

	public AssetClass getAssetClass() {
		return assetClass;
	}

	public void setAssetClass(AssetClass assetClass) {
		this.assetClass = assetClass;
	}

	public Double getAllocationPercent() {
		return allocationPercent;
	}

	public void setAllocationPercent(Double allocationPercent) {
		this.allocationPercent = allocationPercent;
	}

	public ThemeAllocation(Long id, InvestmentTheme theme, AssetClass assetClass, Double allocationPercent) {
		super();
		this.id = id;
		this.theme = theme;
		this.assetClass = assetClass;
		this.allocationPercent = allocationPercent;
	}

	public ThemeAllocation() {
		super();
	}

    
}
