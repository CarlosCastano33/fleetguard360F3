package com.FleetGuard360F3.domain.entities;

import com.FleetGuard360F3.domain.entities.Route;
import com.FleetGuard360F3.domain.entities.Stop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "route_stops")

public class RouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Route
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    // Relación con Stop
    @ManyToOne
    @JoinColumn(name = "stop_id")
    private Stop stop;

    // Campo adicional: orden de parada en la ruta
    private int orderInRoute;

    private boolean firstStop;
    private boolean lastStop;
    private LocalTime timeInStop;
}
