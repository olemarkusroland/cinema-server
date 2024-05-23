package cinema.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotNull(message = "'title' cannot be null")
    public String title;

    @NotNull(message = "'rating' cannot be null")
    public String rating;

    @NotNull(message = "'description' cannot be null")
    public String description;

    @NotNull(message = "'runtimeMins' cannot be null")
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
