package com.FleetGuard360F3.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DriverVehicleShiftDTO {
    private Long id;
    private Long driverId;
    private Long vehicleId;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
