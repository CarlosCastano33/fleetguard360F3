package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.PassengerDTO;
import com.FleetGuard360F3.services.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final IPassengerService passengerService;

    @Autowired
    public PassengerController(IPassengerService passengerService){
        this.passengerService = passengerService;
    }

    @PostMapping
    public ResponseEntity<PassengerDTO> createPassenger(@RequestBody PassengerDTO passengerDTO){
        PassengerDTO createdPassenger = passengerService.createPassenger(passengerDTO);
        return ResponseEntity.ok(createdPassenger);
    }

}
