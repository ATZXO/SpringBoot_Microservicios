package com.atz.microservices.producto_microservice.service;

import com.atz.microservices.producto_microservice.dto.CategoriaRequest;
import com.atz.microservices.producto_microservice.dto.CategoriaResponse;
import com.atz.microservices.producto_microservice.exceptions.ProductoNotFoundException;
import com.atz.microservices.producto_microservice.repository.CategoriaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public Integer createCategoria(CategoriaRequest categoriaRequest) {
        var categoria = categoriaMapper.toCategoria(categoriaRequest);
        return categoriaRepository.save(categoria).getId();
    }

    public CategoriaResponse getCategoriaById(Integer idCategoria) {
        return categoriaRepository.findById(idCategoria)
                .map(categoriaMapper::toCategoriaResponse)
                .orElseThrow(() -> new ProductoNotFoundException(String.format("Categoria con id %d no encontrada", idCategoria)));
    }

    public List<CategoriaResponse> getAllCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toCategoriaResponse)
                .toList();
    }

    public void updateCategoria(Integer idCategoria, @Valid CategoriaRequest categoriaRequest) {
        categoriaRepository.findById(idCategoria)
                .map(categoria -> {
                    categoria.setNombre(categoriaRequest.nombre());
                    categoria.setDescripcion(categoriaRequest.descripcion());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new ProductoNotFoundException(String.format("Categoria con id %d no encontrada", idCategoria)));
    }
}
