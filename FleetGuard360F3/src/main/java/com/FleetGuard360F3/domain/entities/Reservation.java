package com.FleetGuard360F3.domain.entities;

import java.sql.Timestamp;
import java.util.Date;

public class Reservation {
    private String id;
    private String passenderId;
    private String routeId;
    private String vehicleId;
    private String originStopId;
    private String destinationStopId;
    private Date date;
    private String time;
    private Timestamp timestamp;
}
