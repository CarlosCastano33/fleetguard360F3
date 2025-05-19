package com.FleetGuard360F3.Mappers;

import com.FleetGuard360F3.DTO.RouteStopDTO;
import com.FleetGuard360F3.domain.entities.RouteStop;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteStopMapper {
    RouteStopDTO toDTO(RouteStop routeStop);
    List<RouteStopDTO> toDTOs(List<RouteStop> routeStops);
}
