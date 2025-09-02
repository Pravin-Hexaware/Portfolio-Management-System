package com.pms.backend.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "currencies", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code; // e.g. "INR", "USD", "EUR"

    @Column(nullable = false)
    private String name; // e.g. "Indian Rupee", "US Dollar"

    // Constructors
    public Currency() {}
    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
