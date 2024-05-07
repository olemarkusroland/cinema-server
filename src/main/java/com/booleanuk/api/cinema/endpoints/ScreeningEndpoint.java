package com.booleanuk.api.cinema.endpoints;

import com.booleanuk.api.cinema.models.Response;
import com.booleanuk.api.cinema.models.Screening;
import com.booleanuk.api.cinema.repositories.ScreeningRepository;
import com.booleanuk.api.cinema.services.ResponseUtil;
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
    public ResponseEntity<   Response< List<Screening> >   > getScreenings(@PathVariable int movieId) {
        List<Screening> screenings = repository.findByMovieId(movieId);

        return ResponseUtil.buildResponseEntity(screenings);
    }

    @PostMapping("/movie/{movieId}/screening")
    public ResponseEntity<Response<Screening>> createScreening(@PathVariable long movieId, @Valid @RequestBody Screening inputScreening) {
        inputScreening.movieId = (int) movieId;

        Screening screening = repository.save(inputScreening);

        return ResponseUtil.buildResponseEntity(screening);
    }

    @GetMapping("screening/{screeningId}")
    public ResponseEntity<Response<Screening>> getScreening(@PathVariable long screeningId) {
        Screening screening = repository.findById(screeningId).orElse(null);

        return ResponseUtil.buildResponseEntity(screening);
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
