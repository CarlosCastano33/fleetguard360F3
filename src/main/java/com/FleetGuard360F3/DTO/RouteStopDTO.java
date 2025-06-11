package com.FleetGuard360F3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RouteStopDTO {
    private Long id;
    private Long routeId;
    private Long stopId;
    private Integer stopOrder;
}
