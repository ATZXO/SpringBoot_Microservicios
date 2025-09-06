package com.atz.microservices.producto_microservice.dto;

import lombok.Builder;

@Builder
public record ProductoResponse(
        Integer id,
        String nombre,
        String descripcion,
        Double precio,
        Integer stock,
        Integer categoriaId,
        String categoriaNombre,
        String categoriaDescripcion
) {
}
