package com.booleanuk.api.cinema.models;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String title;
    public String rating;
    public String description;
    public int runtimeMins;
    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    protected Movie() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    public void update(Movie movie) {
        this.title = movie.title;
        this.description = movie.description;
        this.rating = movie.rating;
        this.runtimeMins = movie.runtimeMins;
        this.updatedAt = OffsetDateTime.now(); // Update the updatedAt timestamp
    }
}
