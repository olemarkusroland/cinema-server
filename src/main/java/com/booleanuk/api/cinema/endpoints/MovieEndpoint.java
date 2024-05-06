package com.booleanuk.api.cinema.endpoints;

import com.booleanuk.api.cinema.models.Movie;
import com.booleanuk.api.cinema.repositories.MovieRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/movie")
public class MovieEndpoint {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<Movie>> get() {
        return ResponseEntity.ok(movieRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable long id) {
        return movieRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movie> post(@Valid @RequestBody Movie movie) {
        return ResponseEntity.ok(movieRepository.save(movie));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id, @Valid @RequestBody Movie movieDetails) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.update(movieDetails); // Use the update method
                    return ResponseEntity.ok(movieRepository.save(movie));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable long id) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movieRepository.delete(movie);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
