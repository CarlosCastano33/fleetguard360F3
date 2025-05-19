package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehicleRepository  extends JpaRepository<Vehicle, Long> {
}
