package com.atz.microservices.cliente_microservice.service;

import com.atz.microservices.cliente_microservice.dto.ClienteRequest;
import com.atz.microservices.cliente_microservice.dto.ClienteResponse;
import com.atz.microservices.cliente_microservice.entities.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {

    public Cliente toCliente(ClienteRequest request) {
        return Cliente.builder()
                .nombre(request.nombre())
                .email(request.email())
                .telefono(request.telefono())
                .direccion(request.direccion())
                .ciudad(request.ciudad())
                .build();
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .email(cliente.getEmail())
                .telefono(cliente.getTelefono())
                .direccion(cliente.getDireccion())
                .ciudad(cliente.getCiudad())
                .build();
    }
}
