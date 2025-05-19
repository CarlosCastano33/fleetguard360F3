package com.FleetGuard360F3.services;

import com.FleetGuard360F3.DTO.RouteStopDTO;

import java.util.List;

public interface IRouteStopService {
    List<RouteStopDTO> getAllNonLastStops();
}
