package com.iterbio.crudClients.controllers.exception;

import com.iterbio.crudClients.DTO.ErrorResponseDTO;
import com.iterbio.crudClients.DTO.ValidateErrorDTO;
import com.iterbio.crudClients.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponseDTO body =  ErrorResponseDTO.builder()
                .timestamp(Instant.now())
                .error("Not found.")
                .message(e.getMessage())
                .status(status.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidateErrorDTO> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidateErrorDTO body = ValidateErrorDTO.builder()
                .timestamp(Instant.now())
                .error("Invalid entity.")
                .message("Invalid entity.")
                .status(status.value())
                .path(request.getRequestURI())
                .build();

        for(FieldError f: e.getFieldErrors()){
            body.addValidationList(f.getField(),f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(body);
    }
}


