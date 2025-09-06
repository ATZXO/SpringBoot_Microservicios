package com.atz.microservices.producto_microservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CategoriaResponse(
        Integer id,
        String nombre,
        String descripcion,
        List<ProductoResponse> productos
) {
}
