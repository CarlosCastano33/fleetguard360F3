package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.DTO.RouteDTO;
import com.FleetGuard360F3.Mappers.RouteMapper;
import com.FleetGuard360F3.domain.entities.Route;
import com.FleetGuard360F3.domain.repository.IRouteRepository;
import com.FleetGuard360F3.services.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImp implements IRouteService {
    private final IRouteRepository routeRepository;
    private final RouteMapper routeMapper;

    @Autowired
    public RouteServiceImp(IRouteRepository routeRepository, RouteMapper routeMapper){
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
    }

    @Override
    public RouteDTO createRoute(RouteDTO routeDTO){
        Route route = routeMapper.toEntity(routeDTO);
        Route saved = routeRepository.save(route);
        return routeMapper.toDTO(saved);
    }

    @Override
    public List<RouteDTO> getAllRoutes(){
        List<Route> routes = routeRepository.findAll();
        return routes.stream()
                .map(routeMapper::toDTO)
                .collect(Collectors.toList());
    }

}
