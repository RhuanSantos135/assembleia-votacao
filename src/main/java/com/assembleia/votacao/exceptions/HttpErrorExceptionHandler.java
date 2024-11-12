package com.assembleia.votacao.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@ControllerAdvice
public class HttpErrorExceptionHandler {

    private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String message){
        var error = new ApiError(status.value(), message, LocalDateTime.now());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> badRequest(BadRequestException badRequestException){
        return buildErrorResponse(HttpStatus.BAD_REQUEST,badRequestException.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ApiError> objectNotFoundException(ObjectNotFoundException objectNotFoundException){
        return  buildErrorResponse(HttpStatus.NOT_FOUND, objectNotFoundException.getMessage());
    }
}
