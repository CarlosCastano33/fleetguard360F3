package com.FleetGuard360F3.Mappers;

import com.FleetGuard360F3.DTO.PassengerDTO;
import com.FleetGuard360F3.domain.entities.Passenger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    Passenger toEntity(PassengerDTO passengerDTO);
    PassengerDTO toDTO(Passenger passenger);
}
