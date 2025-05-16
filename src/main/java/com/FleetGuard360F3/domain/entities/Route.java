package com.FleetGuard360F3.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "routes")

public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    @OneToMany(mappedBy = "route")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "route")
    private List<RouteStop> stops;
}
