package com.FleetGuard360F3.services;

import com.FleetGuard360F3.DTO.RouteDTO;
import com.FleetGuard360F3.domain.entities.Route;

import java.util.List;
import java.util.Optional;

public interface IRouteService {
    RouteDTO createRoute(RouteDTO routeDTO);
    List<RouteDTO> getAllRoutes();
    Optional<Route> getRouteById(Long id);
}
