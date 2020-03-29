package com.example.exception;


import com.example.service.MensajesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public final class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MensajesService mensajesService;


    @ExceptionHandler({ResourceNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorDetails> dataHandler(Exception ex, WebRequest request) {
        this.logger.error("Ha sucedido un error", ex);

        return compute(ex, ex.getCause(), request.getDescription(false), getStatus(ex));
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorDetails> dataIntegrityViolationHandler(DataIntegrityViolationException ex, WebRequest request) {
        this.logger.error("Ha sucedido un error", ex);

        return compute(ex, ex.getCause().getCause(), request.getDescription(false), getStatus(ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> genenricExcpetionHandler(Exception ex, WebRequest request) {
        this.logger.error("Ha sucedido un error", ex);

        return compute(ex, ex.getCause(), request.getDescription(false), getStatus(ex));
    }

    private ResponseEntity<ErrorDetails> compute(Throwable exception, Throwable cause, String detail, HttpStatus code){
        final ErrorDetails errorDetails = new ErrorDetails(new Date(), mensajesService.get(exception.getMessage()), mensajesService.get(cause.getMessage()), detail);
    	return new ResponseEntity<>(errorDetails, code);
    }

    private HttpStatus getStatus(Throwable exception) {
        HttpStatus code = HttpStatus.INTERNAL_SERVER_ERROR;
        if ( exception.getClass().getAnnotation(ResponseStatus.class) != null) {
            code = exception.getClass().getAnnotation(ResponseStatus.class).value();
        }
        return code;
    }

}
