package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.domain.entities.Passenger;
import com.FleetGuard360F3.domain.entities.Reservation;
import com.FleetGuard360F3.domain.entities.Route;
import com.FleetGuard360F3.domain.entities.User;
import com.FleetGuard360F3.domain.repository.IPassengerRepository;
import com.FleetGuard360F3.domain.repository.IReservationRepository;
import com.FleetGuard360F3.services.IEmailService;
import com.FleetGuard360F3.services.IReservationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService implements IReservationService {
    private final IReservationRepository reservationRepository;
    private final IPassengerRepository passengerRepository;
    private final IEmailService emailService;

    public ReservationService(IReservationRepository reservationRepository, IPassengerRepository passengerRepository, IEmailService emailService) {
        // Constructor logic if needed
        this.reservationRepository = reservationRepository;
        this.passengerRepository = passengerRepository;
        this.emailService = emailService;
    }

    @Override
    public Optional<Reservation> createReservation(User user, Route route) {
        // Logic to create a reservation
        // This is a placeholder implementation
        Reservation newReservation = new Reservation();
        newReservation.setRoute(route);

        Passenger newPassenger = new Passenger();
        newPassenger.setUser(user);
        newPassenger.setName("Test Name"); // TODO: Replace with actual name logic

        Passenger savedPassenger = passengerRepository.save(newPassenger);

        newReservation.setPassenger(savedPassenger);
        newReservation.setStatus(Reservation.Status.Activa);

        newReservation.setConfirmationNumber(generateConfirmationNumber());

        // Save the reservation to the repository
        Reservation savedReservation = reservationRepository.save(newReservation);
        
        // Send confirmation email
        emailService.sendReservationConfirmationEmail(user.getEmail(), savedReservation.getConfirmationNumber());
        
        return Optional.of(savedReservation);
    }

    private String generateConfirmationNumber() {
        // Logic to generate a unique confirmation number, a 16 character random alphanumeric string
        return java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }
}
