package cinema.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String name;
    public int screenWidthMeter;

    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Seat> seats;

    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    public Auditorium() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    public void update(Auditorium auditorium) {
        this.name = auditorium.name;
    }
}
