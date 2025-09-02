package com.pms.backend.entity;
import jakarta.persistence.*;
 
 
@Entity
public class Rebalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String frequency;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public Rebalance(Long id, String frequency) {
        this.id = id;
        this.frequency = frequency;
    }
 
    public String getFrequency() {
        return frequency;
    }
 
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
 
    public Rebalance() {
    }
 
    // Getters and Setters
}