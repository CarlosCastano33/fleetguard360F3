package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.ReservationDTO;
import com.FleetGuard360F3.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reservation")

public class ReservationController {
    private final IReservationService reservationService;

    @Autowired
    public ReservationController(IReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO){
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(createdReservation);
    }
}
