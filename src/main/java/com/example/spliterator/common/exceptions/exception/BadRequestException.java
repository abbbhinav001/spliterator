package com.example.spliterator.common.exceptions.exception;

import java.util.List;

public class BadRequestException extends CommonException {
    /**
     * Passes exception message and error list to the super class
     *
     * @param errorModelList list of errors
     */
    public BadRequestException(String message, List<String> errorModelList) {
        super(message, errorModelList);
    }
}
