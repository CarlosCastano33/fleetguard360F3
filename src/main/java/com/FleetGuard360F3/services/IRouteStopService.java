package com.FleetGuard360F3.services;

import com.FleetGuard360F3.DTO.RouteStopDTO;

import java.util.List;

public interface IRouteStopService {
    RouteStopDTO createRouteStop(RouteStopDTO routeStopDTO);
    List<RouteStopDTO> findByLastStopFalse();
    List<RouteStopDTO> getDestinationsFromOrigin(String origin);
    List<RouteStopDTO> getAllNonLastStops();     //MUESTRA DUPLICADOS
}
