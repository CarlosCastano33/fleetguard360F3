package com.FleetGuard360F3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservationDTO {
    private Long id;
    private String passenger;
    private String route;
    private String vehicle;
    private String originStopName;
    private String destinationStopName;
    private LocalDate date;
    private LocalTime time;
    private Timestamp timestamp;        //PARA HACER PRUEBAS
}
