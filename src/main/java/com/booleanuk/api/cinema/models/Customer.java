package com.booleanuk.api.cinema.models;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String name;
    public String email;
    public String phone;
    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    protected Customer() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    public void update(Customer customer) {
        this.name = customer.name;
        this.email = customer.email;
        this.phone = customer.phone;
        this.updatedAt = OffsetDateTime.now();
    }
}
