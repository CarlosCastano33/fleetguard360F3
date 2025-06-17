package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.PassengerDTO;
import com.FleetGuard360F3.services.IPassengerService;
import com.FleetGuard360F3.utils.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // permite cualquier origen
@RestController
@RequestMapping("/api/passenger")
public class PassengerController {

    @GetMapping()
    public String healthCheck() {
        return "HEALTH CHECK OK";
    }

    private final IPassengerService passengerService;

    //JWT
    private JwtUtil jwtUtil;

    @Autowired
    public PassengerController(IPassengerService passengerService, JwtUtil jwtUtil) {
        this.passengerService = passengerService;
        this.jwtUtil = jwtUtil;
    }
//
//    @Autowired
//    public PassengerController(IPassengerService passengerService){
//        this.passengerService = passengerService;
//    }

    @PostMapping
    public ResponseEntity<PassengerDTO> createPassenger(@RequestBody PassengerDTO passengerDTO){
        PassengerDTO createdPassenger = passengerService.createPassenger(passengerDTO);
        return ResponseEntity.ok(createdPassenger);
    }


    @GetMapping("/all")
    public ResponseEntity<List<PassengerDTO>> getAllPassengers() {
        List<PassengerDTO> passengersDTO = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengersDTO);
    }

}
