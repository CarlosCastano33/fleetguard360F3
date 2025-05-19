package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.RouteStopDTO;
import com.FleetGuard360F3.servicesImp.RouteStopServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/route-stops")
public class RouteStopController {

    private final RouteStopServiceImp routeStopService;

    @Autowired
    public RouteStopController(RouteStopServiceImp routeStopService) {
        this.routeStopService = routeStopService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<RouteStopDTO>> getAvailableRouteStops() {
        List<RouteStopDTO> stops = routeStopService.getAllNonLastStops();
        return ResponseEntity.ok(stops);
    }
}

