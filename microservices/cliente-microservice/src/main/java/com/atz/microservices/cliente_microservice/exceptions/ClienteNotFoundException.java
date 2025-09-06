package com.atz.microservices.cliente_microservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClienteNotFoundException extends RuntimeException {
    private final String message;
}
