package com.atz.microservices.cliente_microservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequest(
        String id,
        @NotNull(message = "El nombre no puede estar vacío")
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,
        @NotNull(message = "El email no puede estar vacío")
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe tener un formato válido")
        String email,
        String telefono,
        String direccion,
        String ciudad
) {
}
