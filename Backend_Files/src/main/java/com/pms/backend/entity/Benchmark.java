package com.pms.backend.entity;
 
import jakarta.persistence.*;
 
@Entity
public class Benchmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String name;
 
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
 
    public Benchmark() {
    }
 
    public Benchmark(Long id, String name) {
        this.id = id;
        this.name = name;
    }
 
    // Getters and Setters
}
 
 