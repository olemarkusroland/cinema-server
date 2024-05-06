package com.booleanuk.api.cinema.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String title;
    public String rating;
    public String description;
    public int runtimeMins;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    protected Movie() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void update(Movie movie) {
        this.title = movie.title;
        this.description = movie.description;
        this.rating = movie.rating;
        this.runtimeMins = movie.runtimeMins;
        this.updatedAt = LocalDateTime.now(); // Update the updatedAt timestamp
    }
}
