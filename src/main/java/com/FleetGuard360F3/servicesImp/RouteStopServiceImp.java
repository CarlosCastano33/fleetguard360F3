package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.DTO.RouteStopDTO;
import com.FleetGuard360F3.Mappers.RouteStopMapper;
import com.FleetGuard360F3.domain.entities.RouteStop;
import com.FleetGuard360F3.domain.repository.IRouteStopRepository;
import com.FleetGuard360F3.services.IRouteStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<RouteStop> origins = routeStopRepository.findByLastStopFalse();
        return routeStopMapper.toDTOs(origins);
    }

//    @Override
//    public List<RouteStopDTO> getDestinationsFromOrigin(String origin){
//        List<RouteStop> originStops = routeStopRepository.findByStopNameAndLastStopFalse(origin);
//        Set<Long> addedStopIds = new HashSet<>(); // evitar duplicados
//        List<RouteStopDTO> destinationDTOs = new ArrayList<>();
//
//        for (RouteStop originStop : originStops) {
//            List<RouteStop> stopsInRoute = routeStopRepository.findByRouteIdOrderByStopOrderAsc(
//                    originStop.getRoute().getId());
//
//            boolean foundOrigin = false;
//            for (RouteStop stop : stopsInRoute) {
//                if (stop.getId().equals(originStop.getId())) {
//                    foundOrigin = true;
//                    continue;
//                }
//                if (foundOrigin && addedStopIds.add(stop.getStop().getId())) {
//                    destinationDTOs.add(routeStopMapper.toDTO(stop));
//                }
//            }
//        }

//        return destinationDTOs;
//    }
}
