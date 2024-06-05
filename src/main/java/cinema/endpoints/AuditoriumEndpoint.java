package cinema.endpoints;

import cinema.exceptions.ResourceNotFoundException;
import cinema.models.Auditorium;
import cinema.models.AuditoriumWhitoutSeats;
import cinema.models.Movie;
import cinema.models.Response;
import cinema.repositories.AuditoriumRepository;
import cinema.services.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="auditorium")
public class AuditoriumEndpoint {
    @Autowired
    private AuditoriumRepository repository;

    @GetMapping
    public ResponseEntity<Response<?>> getAuditoriums() {
        List<Auditorium> auditoriums = repository.findAll();
        List<AuditoriumWhitoutSeats> simplifiedAuditoriums = auditoriums.stream()
                .map(AuditoriumWhitoutSeats::new)
                .collect(Collectors.toList());

        return ResponseUtil.buildResponse(simplifiedAuditoriums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getAuditorium(@PathVariable long id) {
        Auditorium auditorium = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auditorium with ID " + id + " not found"));

        return ResponseUtil.buildResponse(auditorium);
    }
}
