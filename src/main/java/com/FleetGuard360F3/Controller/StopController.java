package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.StopDTO;
import com.FleetGuard360F3.services.IStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // permite cualquier origen
@RestController
@RequestMapping("/api/stop")
public class StopController {

    private final IStopService stopService;

    @Autowired
    public StopController(IStopService stopService){
        this.stopService = stopService;
    }

    @PostMapping
    public ResponseEntity<StopDTO> createStop(@RequestBody StopDTO stopDTO){
        StopDTO createdStop = stopService.createStop(stopDTO);
        return ResponseEntity.ok(createdStop);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StopDTO>> getAllStops(){
        List<StopDTO> stopsDTO = stopService.getAllStops();
        return ResponseEntity.ok(stopsDTO);
    }

}
