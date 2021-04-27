package com.foxrider.rest_server.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ControllerAdvisor {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ErrorMessage> handle(RuntimeException ex) {
        LOG.warn("ExceptionHandler working, {}", ex.getMessage());
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
