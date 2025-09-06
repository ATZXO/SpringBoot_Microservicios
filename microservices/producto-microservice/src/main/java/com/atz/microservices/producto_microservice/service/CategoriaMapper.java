package com.atz.microservices.producto_microservice.service;

import com.atz.microservices.producto_microservice.dto.CategoriaRequest;
import com.atz.microservices.producto_microservice.dto.CategoriaResponse;
import com.atz.microservices.producto_microservice.entities.Categoria;
import org.springframework.stereotype.Service;

@Service
public class CategoriaMapper {

    public Categoria toCategoria(CategoriaRequest categoriaRequest) {
        return Categoria.builder()
                .nombre(categoriaRequest.nombre())
                .descripcion(categoriaRequest.descripcion())
                .build();
    }

    public CategoriaResponse toCategoriaResponse(Categoria categoria) {
        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .productos(categoria.getProductos().stream()
                        .map(ProductoMapper::toProductoResponse)
                        .toList())
                .build();
    }


}
