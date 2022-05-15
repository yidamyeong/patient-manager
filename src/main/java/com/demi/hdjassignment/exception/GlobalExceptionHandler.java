package com.demi.hdjassignment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.UnexpectedTypeException;
import java.security.InvalidParameterException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Error MethodNotAllowedHandler(HttpRequestMethodNotSupportedException e) {
        return new Error(405, "METHOD NOT ALLOWED", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class,
            IllegalStateException.class,
            MethodArgumentTypeMismatchException.class})
    public Error illegalArgumentExceptionHandler(Exception e) {
        log.error("## Illegal Argument Exception\n", e);
        return new Error(400, "ILLEGAL ARGUMENT", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidParameterException.class,
            MethodArgumentNotValidException.class,
            UnexpectedTypeException.class,
            InvalidDataAccessApiUsageException.class,
            MissingRequestHeaderException.class,
            EmptyResultDataAccessException.class})
    public Error invalidParameterExceptionHandler(Exception e) {
        log.error("## Invalid Parameter Exception\n", e);
        return new Error(400, "INVALID PARAMETER", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Error defaultException(Exception e) {
        log.error("## Internal server error\n", e);
        return new Error(500, "INTERNAL SERVER ERROR", e.getMessage());
    }

}

@Data
@AllArgsConstructor
class Error {
    private int errorCode;
    private String message;
    private Object description;
}
