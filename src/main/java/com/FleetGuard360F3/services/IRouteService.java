package com.FleetGuard360F3.services;

import com.FleetGuard360F3.DTO.RouteDTO;

import java.util.List;

public interface IRouteService {
    RouteDTO createRoute(RouteDTO routeDTO);
    List<RouteDTO> getAllRoutes();
}
