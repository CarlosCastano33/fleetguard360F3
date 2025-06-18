package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRouteRepository  extends JpaRepository<Route, String> {
    Optional<Route> findRouteById(String id);
}
