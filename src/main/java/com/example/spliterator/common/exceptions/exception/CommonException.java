package com.example.spliterator.common.exceptions.exception;

import lombok.Data;

import java.io.Serial;
import java.util.List;

@Data
public class CommonException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    // List of Error messages
    private List<String> errors;

    public CommonException() {
        super();
    }

    public CommonException(String message, List<String> errorModels) {
        super(message);
        this.errors = errorModels;
    }
}
