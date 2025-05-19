package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRouteRepository  extends JpaRepository<Route, Long> {
}
