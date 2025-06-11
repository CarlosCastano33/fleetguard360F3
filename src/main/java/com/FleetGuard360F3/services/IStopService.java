package com.FleetGuard360F3.services;

import com.FleetGuard360F3.DTO.StopDTO;

import java.util.List;

public interface IStopService {
    StopDTO createStop(StopDTO stopDTO);
    List<StopDTO> getAllStops();
}
