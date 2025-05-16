package com.FleetGuard360F3.domain.repository;

import com.FleetGuard360F3.domain.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISurveyRepository  extends JpaRepository<Survey, String> {
}
