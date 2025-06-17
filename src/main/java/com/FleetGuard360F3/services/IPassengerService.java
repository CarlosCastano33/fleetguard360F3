package com.FleetGuard360F3.services;

import com.FleetGuard360F3.DTO.PassengerDTO;

import java.util.List;

public interface IPassengerService {
    PassengerDTO createPassenger(PassengerDTO passengerDTO);
    List<PassengerDTO> getAllPassengers();
}
