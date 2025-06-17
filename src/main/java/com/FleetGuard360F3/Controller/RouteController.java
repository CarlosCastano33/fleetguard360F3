package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.RouteDTO;
import com.FleetGuard360F3.services.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/route")

public class RouteController {

    private final IRouteService routeService;

    @Autowired
    public RouteController(IRouteService routeService){
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO){
        RouteDTO createdRoute = routeService.createRoute(routeDTO);
        return ResponseEntity.ok(createdRoute);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RouteDTO>> getAllRoutes(){
        List<RouteDTO> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

}
