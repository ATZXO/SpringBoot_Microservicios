package com.atz.microservices.cliente_microservice.controller;

import com.atz.microservices.cliente_microservice.dto.ClienteRequest;
import com.atz.microservices.cliente_microservice.dto.ClienteResponse;
import com.atz.microservices.cliente_microservice.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<String> createCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
        return ResponseEntity.ok(clienteService.createCliente(clienteRequest));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteResponse> getClienteById(@PathVariable String idCliente) {
        return ResponseEntity.ok(clienteService.getClienteById(idCliente));
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Void> updateCliente(@PathVariable String idCliente, @Valid @RequestBody ClienteRequest clienteRequest) {
        clienteService.updateCliente(idCliente, clienteRequest);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable String idCliente) {
        clienteService.deleteCliente(idCliente);
        return ResponseEntity.accepted().build();
    }


}
