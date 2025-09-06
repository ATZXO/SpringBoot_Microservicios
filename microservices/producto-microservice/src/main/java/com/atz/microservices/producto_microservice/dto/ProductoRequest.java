package com.atz.microservices.producto_microservice.dto;

import jakarta.validation.constraints.NotNull;

public record ProductoRequest(
        Integer id,
        @NotNull(message = "El nombre no puede ser nulo")
        String nombre,
        String descripcion,
        @NotNull(message = "El precio no puede ser nulo")
        Double precio,
        Integer stock,
        @NotNull(message = "La categoriaId no puede ser nula")
        Integer categoriaId
) {
}
