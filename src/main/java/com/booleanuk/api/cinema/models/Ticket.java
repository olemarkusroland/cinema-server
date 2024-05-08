package com.booleanuk.api.cinema.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotNull(message = "'seat' cannot be null")
    public Integer seat;

    public Integer customerId;
    public Integer screeningId;

    protected OffsetDateTime createdAt;
    protected OffsetDateTime updatedAt;

    protected Ticket() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    public void update(Ticket ticket) {
        this.seat = ticket.seat;
        this.customerId = ticket.customerId;
        this.screeningId = ticket.screeningId;
    }
}