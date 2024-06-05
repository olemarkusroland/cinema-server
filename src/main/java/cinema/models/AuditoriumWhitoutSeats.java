package cinema.models;

public class AuditoriumWhitoutSeats {
    public Integer id;
    public String name;
    public int screenWidthMeter;

    public AuditoriumWhitoutSeats() {}

    public AuditoriumWhitoutSeats(Auditorium auditorium) {
        this.id = auditorium.id;
        this.name = auditorium.name;
        this.screenWidthMeter = auditorium.screenWidthMeter;
    }
}