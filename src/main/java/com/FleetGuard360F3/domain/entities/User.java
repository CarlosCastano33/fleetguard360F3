package com.FleetGuard360F3.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    @Email(message = "El correo electrónico no es valido")
    private String email;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "El número de teléfono no es válido")
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean isActive = false;
}
