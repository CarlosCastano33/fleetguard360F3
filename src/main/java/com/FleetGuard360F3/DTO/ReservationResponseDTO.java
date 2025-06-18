package com.FleetGuard360F3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationResponseDTO {
    private String confirmationNumber;
    private String message;
    private String error;

    public static ReservationResponseDTO withMessage(String confirmationNumber, String message) {
        return new ReservationResponseDTO(confirmationNumber, message, null);
    }

    public static ReservationResponseDTO withError(String errorMessage) {
        return new ReservationResponseDTO(null, null, errorMessage);
    }
}
