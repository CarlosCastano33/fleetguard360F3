package com.FleetGuard360F3.Mappers;

import com.FleetGuard360F3.DTO.ReservationDTO;
import com.FleetGuard360F3.domain.entities.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation toEntoty(ReservationDTO reservationDTO);
    ReservationDTO toDTO(Reservation reservation);
}
