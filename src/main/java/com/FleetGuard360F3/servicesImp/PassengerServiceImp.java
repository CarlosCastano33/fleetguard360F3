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

import java.util.List;
import java.util.stream.Collectors;

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
        return passengerMapper.toDTO(saved);
    }

    @Override
    public List<PassengerDTO> getAllPassengers(){
        List<Passenger> passengers = passengerRepository.findAll();
        return passengers.stream()
                .map(passengerMapper::toDTO)
                .collect(Collectors.toList());
    }

}
