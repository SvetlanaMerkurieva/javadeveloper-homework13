package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Pattern(regexp = "^[A-Z0-9]+$")
    private String id;

    @Column
    @Length(min = 1, max = 500, message = "Planet's name must be between 1 and 500 characters")
    private  String name;

    @OneToMany(mappedBy = "planetFrom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> ticketsFrom = new HashSet<>();

    @OneToMany(mappedBy = "planetTo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> ticketsTo = new HashSet<>();

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicketsFrom(Set<Ticket> ticketsFrom) {
        this.ticketsFrom = ticketsFrom;
    }

    public void setTicketsTo(Set<Ticket> ticketsTo) {
        this.ticketsTo = ticketsTo;
    }

    @Override
    public String toString() {
        return "Planet { " +
                "id = '" + id + '\'' +
                ", name = '" + name + '\'' +
                '}';
    }
}
