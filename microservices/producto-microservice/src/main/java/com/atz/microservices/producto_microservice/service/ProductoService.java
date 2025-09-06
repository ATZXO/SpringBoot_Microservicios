package com.atz.microservices.producto_microservice.service;

import com.atz.microservices.producto_microservice.dto.ProductoCantidadRequest;
import com.atz.microservices.producto_microservice.dto.ProductoRequest;
import com.atz.microservices.producto_microservice.dto.ProductoResponse;
import com.atz.microservices.producto_microservice.entities.Producto;
import com.atz.microservices.producto_microservice.exceptions.ProductoNotFoundException;
import com.atz.microservices.producto_microservice.repository.ProductoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaService categoriaService;
    private final ProductoMapper productoMapper;


    public Integer createProducto(@Valid ProductoRequest productoRequest) {
        if(categoriaService.getCategoriaById(productoRequest.categoriaId()) == null) {
            throw new ProductoNotFoundException(String.format("Categoria con id %d no encontrada", productoRequest.categoriaId()));
        }
        Producto producto = productoMapper.toProducto(productoRequest);
        return productoRepository.save(producto).getId();
    }

    public List<ProductoResponse> getAllProductos() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoMapper::toProductoResponse)
                .toList();
    }

    public ProductoResponse getProductoById(Integer idProducto) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new ProductoNotFoundException(String.format("Producto con id %d no encontrado", idProducto)));
        return ProductoMapper.toProductoResponse(producto);
    }

    public void updateProducto(Integer idProducto, @Valid ProductoRequest productoRequest) {
        if(categoriaService.getCategoriaById(productoRequest.categoriaId()) == null) {
            throw new ProductoNotFoundException(String.format("Categoria con id %d no encontrada", productoRequest.categoriaId()));
        }
        productoRepository.findById(idProducto)
                .map(producto -> {
                    producto.setNombre(productoRequest.nombre());
                    producto.setDescripcion(productoRequest.descripcion());
                    producto.setPrecio(productoRequest.precio());
                    producto.setStock(productoRequest.stock());
                    producto.getCategoria().setId(productoRequest.categoriaId());
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new ProductoNotFoundException(String.format("Producto con id %d no encontrado", idProducto)));
    }

    public void deleteProducto(Integer idProducto) {
        if (!productoRepository.existsById(idProducto)) {
            throw new ProductoNotFoundException(String.format("Producto con id %d no encontrado", idProducto));
        }
        productoRepository.deleteById(idProducto);
    }

    public List<ProductoResponse> getProductosByCategoriaId(Integer categoriaId) {
        if(categoriaId == null || categoriaId <= 0) {
            throw new ProductoNotFoundException("El id de la categoria no es valido");
        }
        return productoRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(ProductoMapper::toProductoResponse)
                .toList();
    }

    @Transactional
    public void comprarProducto(@Valid List<ProductoCantidadRequest> request) {
        for (ProductoCantidadRequest item : request) {
            Producto producto = productoRepository.findById(item.idProducto())
                    .orElseThrow(() -> new ProductoNotFoundException(String.format("Producto con id %d no encontrado", item.idProducto())));
            if(item.cantidad() < 0) {
                throw new ProductoNotFoundException(String.format("La cantidad a comprar no puede ser negativa para el producto con id %d", item.idProducto()));
            }
            if (producto.getStock() < item.cantidad()) {
                throw new ProductoNotFoundException(String.format("No hay suficiente stock para el producto con id %d", item.idProducto()));
            }
            producto.setStock(producto.getStock() - item.cantidad());
            productoRepository.save(producto);
        }
    }

    @Transactional
    public void restockProducto(@Valid List<ProductoCantidadRequest> request) {
        for (ProductoCantidadRequest item : request) {
            Producto producto = productoRepository.findById(item.idProducto())
                    .orElseThrow(() -> new ProductoNotFoundException(String.format("Producto con id %d no encontrado", item.idProducto())));
            if(item.cantidad() < 0) {
                throw new ProductoNotFoundException(String.format("La cantidad a reabastecer no puede ser negativa para el producto con id %d", item.idProducto()));
            }
            producto.setStock(producto.getStock() + item.cantidad());
            productoRepository.save(producto);
        }
    }
}
