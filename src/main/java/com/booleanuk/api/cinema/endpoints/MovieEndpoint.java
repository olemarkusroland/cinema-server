package com.booleanuk.api.cinema.endpoints;

import com.booleanuk.api.cinema.exceptions.ResourceNotFoundException;
import com.booleanuk.api.cinema.models.Movie;
import com.booleanuk.api.cinema.models.Response;
import com.booleanuk.api.cinema.repositories.MovieRepository;
import com.booleanuk.api.cinema.services.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/movie")
public class MovieEndpoint {
    @Autowired
    private MovieRepository repository  ;

    @GetMapping
    public ResponseEntity<Response<?>> getMovies() {
        List<Movie> movies = repository.findAll();

        return ResponseUtil.buildResponse(movies);
    }

    @PostMapping
    public ResponseEntity<Response<?>> createMovie(@Valid @RequestBody Movie inputMovie) {
        Movie movie = repository.save(inputMovie);

        return ResponseUtil.buildResponse(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getMovieById(@PathVariable long id) {
        Movie movie = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with ID " + id + " not found"));

        return ResponseUtil.buildResponse(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> updateMovie(@PathVariable long id, @Valid @RequestBody Movie movieDetails) {
        Movie movie = repository.findById(id)
                .map(m -> {
                    m.update(movieDetails);
                    return m;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Movie with ID " + id + " not found"));

        return ResponseUtil.buildResponse(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteMovie(@PathVariable long id) {
        Movie movie = repository.findById(id)
                .map(m -> {
                    repository.delete(m);
                    return m;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Movie with ID " + id + " not found"));

        return ResponseUtil.buildResponse(movie);
    }
}
