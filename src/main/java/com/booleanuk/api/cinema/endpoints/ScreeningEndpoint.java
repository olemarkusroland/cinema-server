package com.booleanuk.api.cinema.endpoints;

import com.booleanuk.api.cinema.exceptions.ResourceNotFoundException;
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
    public ResponseEntity<Response<?>> getScreenings(@PathVariable int movieId) {
        List<Screening> screenings = repository.findByMovieId(movieId);

        return ResponseUtil.buildResponse(screenings);
    }

    @GetMapping("screening/{screeningId}")
    public ResponseEntity<Response<?>> getScreening(@PathVariable long screeningId) {
        Screening screening = repository.findById(screeningId)
                .orElseThrow(() -> new ResourceNotFoundException("Screening with id " + screeningId + " not found"));

        return ResponseUtil.buildResponse(screening);
    }

    @PostMapping("/movie/{movieId}/screening")
    public ResponseEntity<Response<?>> createScreening(@PathVariable long movieId, @Valid @RequestBody Screening inputScreening) {
        inputScreening.movieId = (int) movieId;

        Screening screening = repository.save(inputScreening);

        return ResponseUtil.buildResponse(screening);
    }

    @PutMapping("screening/{screeningId}")
    public ResponseEntity<Response<?>> updateScreening(@Valid @RequestBody Screening screeningInput, @PathVariable long screeningId) {
        return repository.findById(screeningId)
                .map(screening -> {
                    screening.update(screeningInput);
                    return ResponseUtil.buildSuccessResponse(screening);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Screening with id " + screeningId + " not found"));
    }

    @DeleteMapping("screening/{screeningId}")
    public ResponseEntity<Response<?>> deleteScreening(@PathVariable long screeningId) {
        return repository.findById(screeningId)
                .map(screening -> {
                    repository.delete(screening);
                    return ResponseUtil.buildSuccessResponse(screening);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Screening with id " + screeningId + " not found"));
    }
}
