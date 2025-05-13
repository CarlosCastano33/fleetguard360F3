package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.PassengerDTO;
import com.FleetGuard360F3.domain.entities.Passenger;
import com.FleetGuard360F3.domain.repository.IPassengerRepository;
import com.FleetGuard360F3.services.IPassengerService;

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
