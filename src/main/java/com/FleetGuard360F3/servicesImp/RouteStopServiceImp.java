package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.DTO.RouteStopDTO;
import com.FleetGuard360F3.Mappers.RouteStopMapper;
import com.FleetGuard360F3.domain.entities.RouteStop;
import com.FleetGuard360F3.domain.repository.IRouteStopRepository;
import com.FleetGuard360F3.services.IRouteStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteStopServiceImp implements IRouteStopService {
    private final IRouteStopRepository routeStopRepository;
    private final RouteStopMapper routeStopMapper;

    @Autowired
    public RouteStopServiceImp(IRouteStopRepository routeStopRepository, RouteStopMapper routeStopMapper) {
        this.routeStopRepository = routeStopRepository;
        this.routeStopMapper = routeStopMapper;
    }

    @Override
    public List<RouteStopDTO> getAllNonLastStops() {
        List<RouteStop> stops = routeStopRepository.findByLastStopFalse();
        return routeStopMapper.toDTOs(stops);
    }
}
