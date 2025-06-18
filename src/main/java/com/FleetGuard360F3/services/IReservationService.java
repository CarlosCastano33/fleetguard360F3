package com.FleetGuard360F3.services;

import com.FleetGuard360F3.domain.entities.Reservation;
import com.FleetGuard360F3.domain.entities.Route;
import com.FleetGuard360F3.domain.entities.User;

import java.util.Optional;

public interface IReservationService {
    public Optional<Reservation> createReservation(User user, Route route);
}
