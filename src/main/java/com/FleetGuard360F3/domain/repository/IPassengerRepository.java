package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPassengerRepository  extends JpaRepository<Passenger, Long>{

}
