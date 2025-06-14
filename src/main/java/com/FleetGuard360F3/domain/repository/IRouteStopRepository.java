package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRouteStopRepository extends JpaRepository<RouteStop, Long> {
    List<RouteStop> findByLastStopFalse();
    List<RouteStop> findByStop_NameAndLastStopFalse(String origin);
    // Para obtener paradas por ruta ordenadas (útil para destinos y horarios)
    List<RouteStop> findByRouteIdOrderByStopOrderAsc(Long routeId);
    // Para encontrar los horarios de una parada específica
    List<RouteStop> findByRouteIdAndStopId(Long routeId, Long stopId);

    RouteStop findByStop_Name(String stopName);
}
