package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.DTO.RouteStopDTO;
import com.FleetGuard360F3.Mappers.RouteStopMapper;
import com.FleetGuard360F3.domain.entities.RouteStop;
import com.FleetGuard360F3.domain.repository.IRouteStopRepository;
import com.FleetGuard360F3.services.IRouteService;
import com.FleetGuard360F3.services.IRouteStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteStopServiceImp implements IRouteStopService {
    private final IRouteStopRepository routeStopRepository;
    private final RouteStopMapper routeStopMapper;

    private final IRouteService routeService;

    @Autowired
    public RouteStopServiceImp(IRouteStopRepository routeStopRepository, RouteStopMapper routeStopMapper,IRouteService routeService) {
        this.routeStopRepository = routeStopRepository;
        this.routeStopMapper = routeStopMapper;
        this.routeService = routeService;
    }

    @Override
    public List<RouteStopDTO> findByLastStopFalse(){
        List<RouteStop> origins = routeStopRepository.findByLastStopFalse(); // por ahora usas esto
        return origins.stream()
                .map(routeStopMapper::toDTO)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteStopDTO> getDestinationsFromOrigin(String origin) {
        Set<Long> addedStopIds = new HashSet<>();
        List<RouteStopDTO> destinations = new ArrayList<>();

        return destinations;
    }


    @Override
    public List<RouteStopDTO> getAllNonLastStops() {             //MUESTRA DUPLICADOS
        List<RouteStop> origins = routeStopRepository.findByLastStopFalse();
        return routeStopMapper.toDTOs(origins);
    }
}
