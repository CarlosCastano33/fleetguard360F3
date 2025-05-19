package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRouteStop extends JpaRepository<RouteStop, Long> {
}
