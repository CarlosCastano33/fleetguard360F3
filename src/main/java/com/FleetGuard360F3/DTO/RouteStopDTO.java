package com.FleetGuard360F3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RouteStopDTO {
    private Long id;
    private Long stopId;
    private String stopName;
    private String location;
    private Long routeId;
    private String routeName;
    private Integer stopOrder;
}
