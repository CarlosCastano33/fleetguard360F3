package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.RouteDTO;
import com.FleetGuard360F3.domain.entities.Route;
import com.FleetGuard360F3.services.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/routes")

public class RouteController {

    private final IRouteService routeService;

    @Autowired
    public RouteController(IRouteService routeService){
        this.routeService = routeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RouteDTO>> getRoutes()  {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }
}
