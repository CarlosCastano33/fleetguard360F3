package com.FleetGuard360F3.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "route_stops")
public class RouteStop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    // Relación con Route
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private int stopOrder;
    private boolean firstStop;
    private boolean lastStop;
    private LocalTime timeInStop;
    private String stopName;
}
