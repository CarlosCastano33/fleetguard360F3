package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.DTO.PassengerDTO;
import com.FleetGuard360F3.Mappers.PassengerMapper;
import com.FleetGuard360F3.domain.entities.Passenger;
import com.FleetGuard360F3.domain.repository.IPassengerRepository;
import com.FleetGuard360F3.services.IPassengerService;
import com.FleetGuard360F3.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImp implements IPassengerService {
    private final IPassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Value("${app.base.url}")
    private String baseUrl;

    //JWT
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService;
    //---------------------------------
    @Autowired
    public PassengerServiceImp(IPassengerRepository passengerRepository, PassengerMapper passengerMapper){
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public PassengerDTO createPassenger(PassengerDTO passengerDTO){
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        Passenger saved = passengerRepository.save(passenger);
        sendConfirmationEmail(saved); // 🔔
        return passengerMapper.toDTO(saved);
    }

    private void sendConfirmationEmail(Passenger passenger) {
        String token = jwtUtil.generateToken(passenger.getEmail());
        String confirmationLink = baseUrl + "/api/passenger/confirm?token=" + token;
        String emailBody = "<p>Gracias por registrarte. Haz clic en el siguiente enlace para confirmar tu cuenta:</p>"
                + "<a href=\"" + confirmationLink + "\">Confirmar correo</a>";

        try {
            System.out.println("📩 Enviando correo de confirmación a: " + passenger.getEmail());
            emailService.sendConfirmationEmail(passenger.getEmail(), "Confirma tu cuenta", emailBody);
        } catch (Exception e) {
            e.printStackTrace(); // o usar un logger
        }
    }


}
