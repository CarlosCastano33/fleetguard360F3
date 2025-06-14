package com.FleetGuard360F3.services;

import com.FleetGuard360F3.DTO.StopDTO;
import com.FleetGuard360F3.domain.entities.Stop;

import java.util.List;
import java.util.Optional;

public interface IStopService {
    StopDTO createStop(StopDTO stopDTO);
    List<StopDTO> getAllStops();
    Optional<Stop> getStopById(Long id);
}
