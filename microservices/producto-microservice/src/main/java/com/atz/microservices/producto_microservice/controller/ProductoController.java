package com.atz.microservices.producto_microservice.controller;

import com.atz.microservices.producto_microservice.dto.ProductoCantidadRequest;
import com.atz.microservices.producto_microservice.dto.ProductoRequest;
import com.atz.microservices.producto_microservice.dto.ProductoResponse;
import com.atz.microservices.producto_microservice.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<Integer> createProducto(@Valid @RequestBody ProductoRequest productoRequest) {
        return ResponseEntity.ok(productoService.createProducto(productoRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> getAllProductos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoResponse> getProductoById(@PathVariable Integer idProducto) {
        return ResponseEntity.ok(productoService.getProductoById(idProducto));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponse>> getProductosByCategoriaId(@PathVariable Integer categoriaId) {
        return ResponseEntity.ok(productoService.getProductosByCategoriaId(categoriaId));
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Void> updateProducto(@PathVariable Integer idProducto, @Valid @RequestBody ProductoRequest productoRequest) {
        productoService.updateProducto(idProducto, productoRequest);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer idProducto) {
        productoService.deleteProducto(idProducto);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/comprar")
    public ResponseEntity<Void> comprarProducto(@Valid @RequestBody List<ProductoCantidadRequest> request){
        productoService.comprarProducto(request);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/restock")
    public ResponseEntity<Void> updateProductoStock(@Valid @RequestBody List<ProductoCantidadRequest> request){
        productoService.restockProducto(request);
        return ResponseEntity.accepted().build();
    }

}
