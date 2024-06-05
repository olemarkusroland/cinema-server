package cinema.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public Integer widthCM;

    @Column(nullable = false)
    public Integer iconId;

    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    public SeatType() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    public void update(SeatType seatType) {
        if (seatType.widthCM != null) {
            this.widthCM = seatType.widthCM;
        }
        if (seatType.iconId != null) {
            this.iconId = seatType.iconId;
        }
        this.updatedAt = OffsetDateTime.now();
    }
}