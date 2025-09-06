package com.atz.microservices.producto_microservice.controller;

import com.atz.microservices.producto_microservice.dto.CategoriaRequest;
import com.atz.microservices.producto_microservice.dto.CategoriaResponse;
import com.atz.microservices.producto_microservice.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Integer> createCategoria(@Valid @RequestBody CategoriaRequest categoriaRequest){
        return ResponseEntity.ok(categoriaService.createCategoria(categoriaRequest));
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaResponse> getCategoriaById(@PathVariable Integer idCategoria){
        return ResponseEntity.ok(categoriaService.getCategoriaById(idCategoria));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> getAllCategorias(){
        return ResponseEntity.ok(categoriaService.getAllCategorias());
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<Void> updateCategoria(@PathVariable Integer idCategoria, @Valid @RequestBody CategoriaRequest categoriaRequest){
        categoriaService.updateCategoria(idCategoria, categoriaRequest);
        return ResponseEntity.accepted().build();
    }

}
