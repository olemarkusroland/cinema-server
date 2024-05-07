package com.booleanuk.api.cinema.endpoints;

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
    public ResponseEntity<Response<List<Movie>>> getMovies() {
        List<Movie> movies = repository.findAll();

        return ResponseUtil.buildResponseEntity(movies);
    }

    @PostMapping
    public ResponseEntity<Response<Movie>> createMovie(@Valid @RequestBody Movie inputMovie) {
        Movie movie = repository.save(inputMovie);

        return ResponseUtil.buildResponseEntity(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Movie>> getMovieById(@PathVariable long id) {
        Movie movie = repository.findById(id).orElse(null);

        return ResponseUtil.buildResponseEntity(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Movie>> updateMovie(@PathVariable long id, @Valid @RequestBody Movie movieDetails) {
        Movie movie = repository.findById(id)
                .map(m -> {
                    m.update(movieDetails);
                    return m;
                })
                .orElse(null);

        return ResponseUtil.buildResponseEntity(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Movie>> deleteMovie(@PathVariable long id) {
        Movie movie = repository.findById(id)
                .map(m -> {
                    repository.delete(m);
                    return m;
                })
                .orElse(null);

        return ResponseUtil.buildResponseEntity(movie);
    }
}
