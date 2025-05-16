package com.FleetGuard360F3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PassengerDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;

}
