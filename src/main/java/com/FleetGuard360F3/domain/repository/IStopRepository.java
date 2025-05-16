package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStopRepository  extends JpaRepository<Stop, String> {
}
