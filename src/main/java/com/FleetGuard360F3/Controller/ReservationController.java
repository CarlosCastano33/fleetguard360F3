package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.ReservationRequestDTO;
import com.FleetGuard360F3.DTO.ReservationResponseDTO;
import com.FleetGuard360F3.domain.entities.Reservation;
import com.FleetGuard360F3.domain.entities.Route;
import com.FleetGuard360F3.domain.entities.User;
import com.FleetGuard360F3.services.IJWTService;
import com.FleetGuard360F3.services.IReservationService;
import com.FleetGuard360F3.services.IRouteService;
import com.FleetGuard360F3.services.IUserService;
import com.FleetGuard360F3.servicesImp.JWTService;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final IRouteService routeService;
    private final IUserService userService;
    private final IReservationService reservationService;
    private final IJWTService jwtService;

    public ReservationController(IRouteService routeService, IUserService userService, IReservationService reservationService, IJWTService jwtService) {
        this.routeService = routeService;
        this.userService = userService;
        this.reservationService = reservationService;
        this.jwtService = jwtService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ReservationResponseDTO> createReservation(@CookieValue(value = "FleetGuard-Auth-Token", required = false) String token, @RequestBody ReservationRequestDTO reservationRequestDTO) {
        Optional<Route> targetRoute = routeService.getRouteById(reservationRequestDTO.getRouteId());

        if (targetRoute.isEmpty()) {
            return ResponseEntity.status(404).body(ReservationResponseDTO.withError("Route not found"));
        }

        Optional<Jwt> userEmail = jwtService.validateToken(token);

        if (userEmail.isEmpty()) {
            return ResponseEntity.status(401).body(ReservationResponseDTO.withError("Unauthorized"));
        }

        Optional<User> user = userService.findUserByEmail(userEmail.get().getSubject());

        if (user.isEmpty()) {
            return ResponseEntity.status(404).body(ReservationResponseDTO.withError("User not found"));
        }

        Optional<Reservation> newReservation = reservationService.createReservation(user.get(), targetRoute.get());

        if (newReservation.isEmpty()) {
            return ResponseEntity.status(500).body(ReservationResponseDTO.withError("Failed to create reservation"));
        }

        return ResponseEntity.status(200).body(ReservationResponseDTO.withMessage(newReservation.get().getConfirmationNumber(), "Reservation created successfully"));
    }
}
