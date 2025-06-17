package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByPhoneNumber(String phoneNumber);
}
