package cinema.services;

import cinema.models.Auditorium;
import cinema.models.Seat;
import cinema.models.SeatType;
import cinema.repositories.AuditoriumRepository;
import cinema.repositories.SeatRepository;
import cinema.repositories.SeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder {
    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatTypeRepository seatTypeRepository;

    @PostConstruct
    public void seedDatabase() {
        seedAuditoriumsAndSeats();
    }

    private void seedAuditoriumsAndSeats() {
        if (auditoriumRepository.count() == 0) {
            List<Auditorium> auditoriums = Arrays.asList(
                    new Auditorium(),
                    new Auditorium(),
                    new Auditorium()
            );

            auditoriums.get(0).name = "Seahaven";
            auditoriums.get(0).screenWidthMeter = 10;

            auditoriums.get(1).name = "Rivendell";
            auditoriums.get(1).screenWidthMeter = 15;

            auditoriums.get(2).name = "Tatooine";
            auditoriums.get(2).screenWidthMeter = 20;

            for (Auditorium auditorium : auditoriums) {
                auditoriumRepository.save(auditorium);
                if (auditorium.name.equals("Seahaven")) {
                    createSeatsForAuditorium(auditorium, 10, 8, 1);
                } else if (auditorium.name.equals("Rivendell")) {
                    createSeatsForAuditorium(auditorium, 15, 12, 2);
                } else if (auditorium.name.equals("Tatooine")) {
                    createSeatsForAuditorium(auditorium, 20, 15, 3);
                }
            }
        }
    }

    private void createSeatsForAuditorium(Auditorium auditorium, int totalRows, int baseSeats, int increment) {
        SeatType defaultSeatType = seatTypeRepository.findByName("Standard");
        if (defaultSeatType == null) {
            defaultSeatType = new SeatType();
            defaultSeatType.name = "Standard";
            defaultSeatType.widthCM = 50;
            defaultSeatType.iconId = 1;
            seatTypeRepository.save(defaultSeatType);
        }

        for (int row = 1; row <= totalRows; row++) {
            int seatsInRow = baseSeats + (row - 1) * increment;
            for (int number = 1; number <= seatsInRow; number++) {
                Seat seat = new Seat();
                seat.row = row;
                seat.number = number;
                seat.setAuditorium(auditorium);
                seat.type = defaultSeatType;
                seatRepository.save(seat);
            }
        }
    }
}