package com.pms.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "portfolio_holdings")
public class PortfolioHolding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "security_id")
    private Security security;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "equity_category")
    private String equityCategory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getEquityCategory() {
		return equityCategory;
	}

	public void setEquityCategory(String equityCategory) {
		this.equityCategory = equityCategory;
	}

	public PortfolioHolding(Long id, Portfolio portfolio, Security security, Integer quantity, BigDecimal price,
			BigDecimal value, String equityCategory) {
		super();
		this.id = id;
		this.portfolio = portfolio;
		this.security = security;
		this.quantity = quantity;
		this.price = price;
		this.value = value;
		this.equityCategory = equityCategory;
	}

	public PortfolioHolding() {
		super();
	}

    
}
