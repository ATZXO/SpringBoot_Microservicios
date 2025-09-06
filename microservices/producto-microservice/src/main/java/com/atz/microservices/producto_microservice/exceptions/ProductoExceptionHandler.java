package com.atz.microservices.producto_microservice.exceptions;

import com.atz.microservices.common_exceptions.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice(basePackages = "com.atz.microservices.producto_microservice")
@Primary
@Slf4j
public class ProductoExceptionHandler {

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(ProductoNotFoundException exception) {
        var errors = new HashMap<String, String>();
        var fieldname = "producto";
        errors.put(fieldname, exception.getMessage());
        //log.error("Producto not found: {}", exception.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }
}
