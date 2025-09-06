package com.atz.microservices.cliente_microservice.service;

import com.atz.microservices.cliente_microservice.dto.ClienteRequest;
import com.atz.microservices.cliente_microservice.dto.ClienteResponse;
import com.atz.microservices.cliente_microservice.entities.Cliente;
import com.atz.microservices.cliente_microservice.exceptions.ClienteNotFoundException;
import com.atz.microservices.cliente_microservice.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public String createCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.toCliente(clienteRequest);
        return clienteRepository.save(cliente).getId();
    }

    public List<ClienteResponse> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toResponse)
                .toList();
    }

    public ClienteResponse getClienteById(String idCliente) {
        return clienteRepository.findById(idCliente)
                .map(clienteMapper::toResponse)
                .orElseThrow(() -> new ClienteNotFoundException(String.format("Cliente con id %s no encontrado", idCliente)));
    }

    public void updateCliente(String idCliente, ClienteRequest clienteRequest) {
        if(clienteRepository.existsById(idCliente)) {
            Cliente cliente = clienteMapper.toCliente(clienteRequest);
            cliente.setId(idCliente);
            clienteRepository.save(cliente);
        } else {
            throw new ClienteNotFoundException(String.format("Cliente con id %s no encontrado", idCliente));
        }
    }

    public void deleteCliente(String idCliente) {
        clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ClienteNotFoundException(String.format("Cliente con id %s no encontrado", idCliente)));
        clienteRepository.deleteById(idCliente);
    }
}
