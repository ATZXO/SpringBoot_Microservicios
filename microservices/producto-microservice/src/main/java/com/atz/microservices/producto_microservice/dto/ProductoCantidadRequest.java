package com.atz.microservices.producto_microservice.dto;

import jakarta.validation.constraints.NotNull;

public record ProductoCantidadRequest(
        @NotNull(message = "El id del producto no puede ser nulo")
        Integer idProducto,
        @NotNull(message = "La cantidad no puede ser nula")
        Integer cantidad
) {
}
