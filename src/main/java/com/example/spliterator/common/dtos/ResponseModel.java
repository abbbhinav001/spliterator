package com.example.spliterator.common.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Basic response model
 */
@Data
@SuperBuilder
public class ResponseModel {
    private int code;

    private String type;

    private String message;
}
