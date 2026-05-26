package com.prolearner.azurepro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "test_records")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    public TestEntity() {}

    public TestEntity(String message) {
        this.message = message;
    }

    public Long getId() { return id; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}