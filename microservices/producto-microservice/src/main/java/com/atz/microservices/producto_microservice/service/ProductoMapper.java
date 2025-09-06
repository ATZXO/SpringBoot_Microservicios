package com.atz.microservices.producto_microservice.service;

import com.atz.microservices.producto_microservice.dto.ProductoRequest;
import com.atz.microservices.producto_microservice.dto.ProductoResponse;
import com.atz.microservices.producto_microservice.entities.Categoria;
import com.atz.microservices.producto_microservice.entities.Producto;
import org.springframework.stereotype.Service;

@Service
public class ProductoMapper {

    public Producto toProducto(ProductoRequest productoRequest) {
        return Producto.builder()
                .nombre(productoRequest.nombre())
                .descripcion(productoRequest.descripcion())
                .precio(productoRequest.precio())
                .stock(productoRequest.stock())
                .categoria(Categoria.builder()
                        .id(productoRequest.categoriaId())
                        .build())
                .build();
    }

    public static ProductoResponse toProductoResponse(Producto producto) {
        return ProductoResponse.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoriaId(producto.getCategoria().getId())
                .categoriaNombre(producto.getCategoria().getNombre())
                .categoriaDescripcion(producto.getCategoria().getDescripcion())
                .build();
    }
}
