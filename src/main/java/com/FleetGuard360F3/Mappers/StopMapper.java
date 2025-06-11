package com.FleetGuard360F3.Mappers;

import com.FleetGuard360F3.DTO.StopDTO;
import com.FleetGuard360F3.domain.entities.Stop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StopMapper {
    Stop toEntity(StopDTO stopDTO);
    StopDTO toDTO(Stop stop);
}
