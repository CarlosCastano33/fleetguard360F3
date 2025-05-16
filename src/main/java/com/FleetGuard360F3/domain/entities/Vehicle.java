package com.FleetGuard360F3.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "vehicles")

public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licencePlate;
    private String currentLocation;         //Si es un String??? qué tipo?

    @OneToMany(mappedBy = "vehicle")
    private List<Reservation> reservations;
}
