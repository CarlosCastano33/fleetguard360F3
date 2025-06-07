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

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmEmail(@RequestParam String token, HttpServletResponse response) {

//        try {
            String email = jwtUtil.getEmailFromToken(token);
            // Aquí actualizo la base de datos para marcar al usuario como confirmado
            Cookie authCookie  = new Cookie("X-FleetGuard360-Auth", token);
            response.addCookie(authCookie);
            //ToDo Crear un nuevo token, de autenticacion
            return ResponseEntity.ok("Cuenta confirmada para: " + email);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
//        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PassengerDTO>> getAllPassengers() {
        List<PassengerDTO> passengersDTO = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengersDTO);
    }

}
