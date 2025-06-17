package com.FleetGuard360F3.Mappers;

import com.FleetGuard360F3.DTO.RouteDTO;
import com.FleetGuard360F3.domain.entities.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    Route toEntity(RouteDTO routeDTO);
    RouteDTO toDTO(Route route);
}
