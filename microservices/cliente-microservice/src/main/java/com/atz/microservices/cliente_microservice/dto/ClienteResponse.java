package com.atz.microservices.cliente_microservice.dto;

import lombok.Builder;

@Builder
public record ClienteResponse(
        String id,
        String nombre,
        String email,
        String telefono,
        String direccion,
        String ciudad
) {
}
