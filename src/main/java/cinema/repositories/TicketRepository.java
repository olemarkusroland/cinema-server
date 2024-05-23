package cinema.repositories;

import cinema.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByCustomerIdAndScreeningId(final long customerId, final long screeningId);
}
