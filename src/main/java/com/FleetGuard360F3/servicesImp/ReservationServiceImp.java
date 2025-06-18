package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.DTO.ReservationDTO;
import com.FleetGuard360F3.Mappers.ReservationMapper;
import com.FleetGuard360F3.domain.entities.Reservation;
import com.FleetGuard360F3.domain.repository.IReservationRepository;
import com.FleetGuard360F3.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImp implements IReservationService {
    private final IReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationServiceImp(IReservationRepository reservationRepository, ReservationMapper reservationMapper){
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }


    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.toEntoty(reservationDTO);
        Reservation saved = reservationRepository.save(reservation);
        return reservationMapper.toDTO(saved);
    }
}
