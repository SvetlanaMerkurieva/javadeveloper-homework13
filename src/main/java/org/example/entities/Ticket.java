package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet planetFrom;

    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet planetTo;

    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setPlanetFrom(Planet planetFrom) {
        this.planetFrom = planetFrom;
    }

    public void setPlanetTo(Planet planetTo) {
        this.planetTo = planetTo;
    }

    @Override
    public String toString() {
        return "Ticket { " +
                "id = " + id +
                ", createdAt = " + createdAt +
                ", client = " + client +
                ", planetFrom = " + planetFrom +
                ", planetTo = " + planetTo +
                '}';
    }
}
