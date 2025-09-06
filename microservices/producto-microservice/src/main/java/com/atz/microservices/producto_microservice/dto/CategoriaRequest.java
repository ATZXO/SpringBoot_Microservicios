package com.atz.microservices.producto_microservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaRequest(
        Integer id,
        @NotNull(message = "El nombre no puede ser nulo")
        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre,
        @NotNull(message = "La descripcion no puede ser nula")
        String descripcion
) {
}
