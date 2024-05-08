package com.booleanuk.api.cinema.endpoints;

import com.booleanuk.api.cinema.exceptions.ResourceNotFoundException;
import com.booleanuk.api.cinema.models.Customer;
import com.booleanuk.api.cinema.models.Screening;
import com.booleanuk.api.cinema.models.Ticket;
import com.booleanuk.api.cinema.models.Response;
import com.booleanuk.api.cinema.repositories.CustomerRepository;
import com.booleanuk.api.cinema.repositories.ScreeningRepository;
import com.booleanuk.api.cinema.repositories.TicketRepository;
import com.booleanuk.api.cinema.services.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class TicketEndpoint {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ScreeningRepository screeningRepository;

    @GetMapping("/customers/{customerId}/screenings/{screeningId}")
    public ResponseEntity<Response<?>> getTickets(@PathVariable long customerId, @PathVariable long screeningId) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + customerId + " not found"));

        screeningRepository.findById(screeningId)
                .orElseThrow(() -> new ResourceNotFoundException("Screening with ID " + screeningId + " not found"));

        List<Ticket> tickets = ticketRepository.findAllByCustomerIdAndScreeningId(customerId, screeningId);

        return ResponseUtil.buildSuccessResponse(tickets);
    }

    @PostMapping("/customers/{customerId}/screenings/{screeningId}")
    public ResponseEntity<Response<?>> createTicket(@PathVariable long customerId,
                                                    @PathVariable long screeningId,
                                                    @Valid @RequestBody Ticket inputTicket) {
        inputTicket.customerId = (int) customerId;
        inputTicket.screeningId = (int) screeningId;

        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + customerId + " not found"));

        screeningRepository.findById(screeningId)
                .orElseThrow(() -> new ResourceNotFoundException("Screening with ID " + screeningId + " not found"));

        Ticket ticket = ticketRepository.save(inputTicket);

        return ResponseUtil.buildSuccessResponse(ticket);
    }

    @GetMapping("ticket/{id}")
    public ResponseEntity<Response<?>> getTicketById(@PathVariable long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket with ID " + id + " not found"));

        return ResponseUtil.buildSuccessResponse(ticket);
    }

    @PutMapping("ticket/{id}")
    public ResponseEntity<Response<?>> updateTicket(@PathVariable long id,
                                                    @Valid @RequestBody Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket with ID " + id + " not found"));

        ticket.update(ticketDetails);
        ticketRepository.save(ticket);
        return ResponseUtil.buildSuccessResponse(ticket);
    }

    @DeleteMapping("ticket/{id}")
    public ResponseEntity<Response<?>> deleteTicket(@PathVariable long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket with ID " + id + " not found"));

        ticketRepository.delete(ticket);
        return ResponseUtil.buildSuccessResponse(ticket);
    }
}