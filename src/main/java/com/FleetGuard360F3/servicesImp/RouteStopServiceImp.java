package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.DTO.RouteDTO;
import com.FleetGuard360F3.DTO.RouteStopDTO;
import com.FleetGuard360F3.Mappers.RouteStopMapper;
import com.FleetGuard360F3.domain.entities.Route;
import com.FleetGuard360F3.domain.entities.RouteStop;
import com.FleetGuard360F3.domain.entities.Stop;
import com.FleetGuard360F3.domain.repository.IRouteStopRepository;
import com.FleetGuard360F3.services.IRouteService;
import com.FleetGuard360F3.services.IRouteStopService;
import com.FleetGuard360F3.services.IStopService;
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
    private final IStopService stopService;

    @Autowired
    public RouteStopServiceImp(IRouteStopRepository routeStopRepository, RouteStopMapper routeStopMapper,IRouteService routeService, IStopService stopService) {
        this.routeStopRepository = routeStopRepository;
        this.routeStopMapper = routeStopMapper;
        this.routeService = routeService;
        this.stopService = stopService;
    }

    @Override
    public RouteStopDTO createRouteStop(RouteStopDTO routeStopDTO){
        RouteStop routeStop = routeStopMapper.toEntity(routeStopDTO);
        RouteStop saved = routeStopRepository.save(routeStop);
        return new RouteStopDTO();
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
        List<RouteStop> originStops = routeStopRepository.findByStop_NameAndLastStopFalse(origin);
        Set<Long> addedStopIds = new HashSet<>();
        List<RouteStopDTO> destinations = new ArrayList<>();

        for (RouteStop originStop : originStops) {
            List<RouteStop> stopsInRoute = routeStopRepository
                    .findByRouteIdOrderByStopOrderAsc(originStop.getRoute().getId());

            boolean passedOrigin = false;

            for (RouteStop stop : stopsInRoute) {
                if (stop.getId().equals(originStop.getId())) {
                    passedOrigin = true;
                    continue;
                }
                if (passedOrigin && addedStopIds.add(stop.getStop().getId())) {
                    destinations.add(routeStopMapper.toDTO(stop));
                }
            }
        }
        return destinations;
    }


    @Override
    public List<RouteStopDTO> getAllNonLastStops() {             //MUESTRA DUPLICADOS
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

    public RouteStop toEntity(RouteStopDTO dto) {
        Route route = routeService.getRouteById(dto.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found with ID: " + dto.getRouteId()));
        Stop stop = stopService.getStopById(dto.getStopId())
                .orElseThrow(() -> new RuntimeException("Stop not found with ID: " + dto.getStopId()));

        RouteStop entity = new RouteStop();
        entity.setId(dto.getId());
        entity.setRoute(route);
        entity.setStop(stop);
        entity.setStopOrder(dto.getStopOrder());

        return entity;
    }

}
