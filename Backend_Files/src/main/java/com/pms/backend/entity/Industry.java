package com.pms.backend.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "industries", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Constructors
    public Industry() {}
    public Industry(String name) {
        this.name = name;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}