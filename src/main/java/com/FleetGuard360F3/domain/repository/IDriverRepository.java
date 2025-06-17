package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDriverRepository extends JpaRepository<Driver, Long>{
}
