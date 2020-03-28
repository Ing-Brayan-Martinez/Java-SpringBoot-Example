package com.example.jdbc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public final class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        this.logger.error("Ha sucedido un error para iniciar secion", ex);

        final ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getCause().getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        this.logger.error("Ha sucedido un error para obtener un recurso", ex);

        final ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getCause().getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> dataHandler(DataIntegrityViolationException ex, WebRequest request) {
        this.logger.error("Ha sucedido un error", ex);

        final ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getCause().getCause().getLocalizedMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        this.logger.error("Ha sucedido un error", ex);

        final ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getCause().getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
