package com.pms.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "currency")
    private String currency;

    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "initial_investment")
    private BigDecimal initialInvestment;

    @Column(name = "current_value")
    private BigDecimal currentValue;

    @Column(name = "rebalancing_frequency")
    private String rebalancingFrequency;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private InvestmentTheme theme;

    @Column(name = "status")
    private String status;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public BigDecimal getInitialInvestment() {
		return initialInvestment;
	}

	public void setInitialInvestment(BigDecimal initialInvestment) {
		this.initialInvestment = initialInvestment;
	}

	public BigDecimal getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(BigDecimal currentValue) {
		this.currentValue = currentValue;
	}

	public String getRebalancingFrequency() {
		return rebalancingFrequency;
	}

	public void setRebalancingFrequency(String rebalancingFrequency) {
		this.rebalancingFrequency = rebalancingFrequency;
	}

	public InvestmentTheme getTheme() {
		return theme;
	}

	public void setTheme(InvestmentTheme theme) {
		this.theme = theme;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Portfolio(Long id, String name, String type, String currency, String benchmark, String exchange,
			BigDecimal initialInvestment, BigDecimal currentValue, String rebalancingFrequency, InvestmentTheme theme,
			String status) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.currency = currency;
		this.benchmark = benchmark;
		this.exchange = exchange;
		this.initialInvestment = initialInvestment;
		this.currentValue = currentValue;
		this.rebalancingFrequency = rebalancingFrequency;
		this.theme = theme;
		this.status = status;
	}

	public Portfolio() {
		super();
	}


	// this is the new line i have added in my branch
    
}
