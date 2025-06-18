package com.FleetGuard360F3.DTO;

import com.FleetGuard360F3.domain.entities.Route;
import com.FleetGuard360F3.domain.entities.RouteStop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
    private String id;
    private String name;

    private String firstStopName;
    private String lastStopName;

    private LocalTime startTime;
    private LocalTime endTime;

    private Integer numberOfStops;
    private Integer price;


    static public RouteDTO from(Route route) {
        List<RouteStop> stops = route.getStops();
        stops.sort(Comparator.comparing(RouteStop::getStopOrder));

        return new RouteDTO(
                route.getId(),
                route.getName(),
                stops.getFirst().getStopName(),
                stops.getLast().getStopName(),
                stops.getFirst().getTimeInStop(),
                stops.getLast().getTimeInStop(),
                stops.size(),
                route.getPrice()
        );
    }
}
