package com.atz.microservices.cliente_microservice.exceptions;

import com.atz.microservices.common_exceptions.ErrorResponse;
import com.atz.microservices.common_exceptions.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice(basePackages = "com.atz.microservices.cliente_microservice")
@Primary
@Slf4j
public class ClienteExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(ClienteNotFoundException exception) {
        var errors = new HashMap<String, String>();
        var fieldname = "cliente";
        errors.put(fieldname, exception.getMessage());
        //log.error("Cliente not found: {}", exception.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }
}
