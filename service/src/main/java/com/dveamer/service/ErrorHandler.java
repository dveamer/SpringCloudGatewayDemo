package com.dveamer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionJson> handle(Exception ex) {
        logger.debug(ex.getMessage());
        return new ResponseEntity<>(new ExceptionJson(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private class ExceptionJson {

        private final String msg;

        ExceptionJson(Throwable ex) {
            this.msg = ex.getMessage();
        }

        public String getMsg() {
            return msg;
        }
    }

}
