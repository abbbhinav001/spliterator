package com.example.spliterator.common.exception.handler;

import com.example.spliterator.common.exception.exceptions.BadRequestException;
import com.example.spliterator.common.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.spliterator.common.constants.MessageType.TYPE_FAILED;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    /**
     * This method processes the Response for when BadRequestException is thrown
     *
     * @param e BadRequestException
     * @return ResponseEntity
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException e) {
        log.info(String.valueOf(e.getCause()));
        ApiError apiError = ApiError
            .builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .type(TYPE_FAILED)
            .errors(e.getErrors())
            .build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
