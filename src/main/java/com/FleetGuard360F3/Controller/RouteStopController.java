package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.RouteStopDTO;
import com.FleetGuard360F3.servicesImp.RouteStopServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/origins")
    public ResponseEntity<List<RouteStopDTO>> getOrigins() {
        List<RouteStopDTO> origins = routeStopService.findByLastStopFalse();
        return ResponseEntity.ok(origins);
    }

    @GetMapping("/destinations")
    public ResponseEntity<List<RouteStopDTO>> getDestinations(@RequestParam String origin) {
        List<RouteStopDTO> destinations = routeStopService.getDestinationsFromOrigin(origin);
        return ResponseEntity.ok(destinations);
    }

}

