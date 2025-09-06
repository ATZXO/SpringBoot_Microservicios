package com.atz.microservices.cliente_microservice.repository;

import com.atz.microservices.cliente_microservice.entities.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
}
