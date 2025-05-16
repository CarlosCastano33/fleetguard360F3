package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository  extends JpaRepository<Reservation, String> {
}
