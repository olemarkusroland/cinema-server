package cinema.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
public class Movie {
    @Id
    @NotNull(message = "'imdbID' cannot be null")
    public String imdbID;

    @NotNull(message = "'title' cannot be null")
    public String title;

    @NotNull(message = "'year' cannot be null")
    public String year;

    @NotNull(message = "'rated' cannot be null")
    public String rated;

    @NotNull(message = "'released' cannot be null")
    public String released;

    @NotNull(message = "'runtime' cannot be null")
    public String runtime;

    @NotNull(message = "'genre' cannot be null")
    public String genre;

    @NotNull(message = "'director' cannot be null")
    public String director;

    @NotNull(message = "'writer' cannot be null")
    public String writer;

    @NotNull(message = "'actors' cannot be null")
    public String actors;

    @NotNull(message = "'plot' cannot be null")
    public String plot;

    @NotNull(message = "'language' cannot be null")
    public String language;

    @NotNull(message = "'country' cannot be null")
    public String country;

    @NotNull(message = "'awards' cannot be null")
    public String awards;

    @NotNull(message = "'poster' cannot be null")
    public String poster;

    @ElementCollection
    @NotNull(message = "'ratings' cannot be null")
    public List<Rating> ratings;

    @NotNull(message = "'metascore' cannot be null")
    public String metascore;

    @NotNull(message = "'imdbRating' cannot be null")
    public String imdbRating;

    @NotNull(message = "'imdbVotes' cannot be null")
    public String imdbVotes;

    @NotNull(message = "'type' cannot be null")
    public String type;

    @NotNull(message = "'dvd' cannot be null")
    public String dvd;

    @NotNull(message = "'boxOffice' cannot be null")
    public String boxOffice;

    @NotNull(message = "'production' cannot be null")
    public String production;

    @NotNull(message = "'website' cannot be null")
    public String website;

    @NotNull(message = "'response' cannot be null")
    public String response;

    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    public Movie() {}

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
        this.year = movie.year;
        this.rated = movie.rated;
        this.released = movie.released;
        this.runtime = movie.runtime;
        this.genre = movie.genre;
        this.director = movie.director;
        this.writer = movie.writer;
        this.actors = movie.actors;
        this.plot = movie.plot;
        this.language = movie.language;
        this.country = movie.country;
        this.awards = movie.awards;
        this.poster = movie.poster;
        this.ratings = movie.ratings;
        this.metascore = movie.metascore;
        this.imdbRating = movie.imdbRating;
        this.imdbVotes = movie.imdbVotes;
        this.type = movie.type;
        this.dvd = movie.dvd;
        this.boxOffice = movie.boxOffice;
        this.production = movie.production;
        this.website = movie.website;
        this.response = movie.response;
    }
}
