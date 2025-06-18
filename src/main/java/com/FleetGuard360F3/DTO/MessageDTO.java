package com.FleetGuard360F3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO {
    private String message;
    private String error;

    static public MessageDTO withMessage(String message) {
        return new MessageDTO(message, null);
    }

    static public MessageDTO withError(String error) {
        return new MessageDTO(null, error);
    }
}
