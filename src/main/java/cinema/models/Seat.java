package cinema.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false)
    public Integer row;

    @Column(nullable = false)
    public Integer number;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    public SeatType type;

    @ManyToOne
    @JoinColumn(name = "auditorium_id", nullable = false)
    private Auditorium auditorium;

    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Seat() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    public void update(Seat seat) {
        if (seat.row != null) {
            this.row = seat.row;
        }
        if (seat.number != null) {
            this.number = seat.number;
        }
        if (seat.auditorium != null) {
            this.auditorium = seat.auditorium;
        }
        if (seat.type != null) {
            this.type = seat.type;
        }
        this.updatedAt = OffsetDateTime.now();
    }
}