package com.FleetGuard360F3.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DriverDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private List<DriverVehicleShiftDTO> shifts;
}
