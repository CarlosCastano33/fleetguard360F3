package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.DTO.StopDTO;
import com.FleetGuard360F3.Mappers.StopMapper;
import com.FleetGuard360F3.domain.entities.Stop;
import com.FleetGuard360F3.domain.repository.IStopRepository;
import com.FleetGuard360F3.services.IStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StopServiceImp implements IStopService {
    private final IStopRepository stopRepository;
    private final StopMapper stopMapper;

    @Autowired
    public StopServiceImp(IStopRepository stopRepository, StopMapper stopMapper){
        this.stopRepository = stopRepository;
        this.stopMapper = stopMapper;
    }

    @Override
    public StopDTO createStop(StopDTO stopDTO){
        Stop stop = stopMapper.toEntity(stopDTO);
        Stop saved = stopRepository.save(stop);
        return stopMapper.toDTO(saved);
    }

    @Override
    public List<StopDTO> getAllStops(){
        List<Stop> stops = stopRepository.findAll();
        return stops.stream()
                .map(stopMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Stop> getStopById(Long id) {
        return stopRepository.findById(id);
    }

}
