package cinema.repositories;

import cinema.models.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {
    SeatType findByName(String name);
}