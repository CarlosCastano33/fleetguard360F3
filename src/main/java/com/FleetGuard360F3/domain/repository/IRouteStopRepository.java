package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRouteStopRepository extends JpaRepository<RouteStop, Long> {
    List<RouteStop> findByLastStopFalse();
}
