package com.FleetGuard360F3.Mappers;

import com.FleetGuard360F3.DTO.RouteStopDTO;
import com.FleetGuard360F3.domain.entities.RouteStop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteStopMapper {
    @Mapping(source = "route.id", target = "routeId")
    @Mapping(source = "route.name", target = "routeName")
//    @Mapping(source = "stopOrder", target = "stopOrder")
    RouteStopDTO toDTO(RouteStop routeStop);
    List<RouteStopDTO> toDTOs(List<RouteStop> routeStops);
}
