package com.atz.microservices.producto_microservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductoNotFoundException extends RuntimeException{
    private final String message;
}
