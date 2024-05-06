package com.booleanuk.api.cinema.endpoints;

import com.booleanuk.api.cinema.models.Screening;
import com.booleanuk.api.cinema.repositories.ScreeningRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ScreeningEndpoint {
    @Autowired
    private ScreeningRepository repository;

    @GetMapping("/movie/{movieId}/screening")
    public ResponseEntity<List<Screening>> getScreenings(@PathVariable int movieId) {
        return ResponseEntity.ok(repository.findByMovieId(movieId));
    }

    @PostMapping("/movie/{movieId}/screening")
    public ResponseEntity<Screening> createScreening(@PathVariable long movieId, @Valid @RequestBody Screening screening) {
        screening.movieId = (int) movieId;
        return ResponseEntity.ok(repository.save(screening));
    }

    @GetMapping("screening/{screeningId}")
    public ResponseEntity<Screening> getScreening(@PathVariable long screeningId) {
        return repository.findById(screeningId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("screening/{screeningId}")
    public ResponseEntity<Screening> updateScreening(@Valid @RequestBody Screening screeningInput, @PathVariable long screeningId) {
        return repository.findById(screeningId)
                .map(screening -> {
                    screening.update(screeningInput);
                    return ResponseEntity.ok(repository.save(screening));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("screening/{screeningId}")
    public ResponseEntity<Screening> deleteScreening(@PathVariable long screeningId) {
        return repository.findById(screeningId)
                .map(screening -> {
                    repository.delete(screening);
                    return ResponseEntity.ok(screening);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
