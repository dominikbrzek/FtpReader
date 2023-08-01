package com.example.ftpreader.infrastructure;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Rest exception handler
 * <br>
 * <p/>
 * Creation date: 01/08/2023<br>
 *
 * @author dobr
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({HttpMessageNotReadableException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingRequestHeaderException.class})
    public ResponseEntity<Error> handleReadRequestException(Exception exception) {
        log.error("Error during reading request: {}", exception.getMessage());
        return new ResponseEntity<>(new Error(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception exception) {
        log.error("Internal server error", exception);
        return new ResponseEntity<>(new Error(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    private static class Error {

        private final Map<String, String> errorData;

        public Error(Exception exception) {
            this.errorData = new HashMap<>();
            this.errorData.put("message", exception.getMessage());
            if (exception.getCause() != null) {
                this.errorData.put("cause", exception.getCause().toString());
            }
        }
    }
}
