package com.example.spliterator.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiError {
    @Schema(description = "Custom code", example = "1000")
    private int code;

    @Schema(description = "HttpStatus", example = "Http Response Status")
    private String type;

    @Schema(description = "List of errors")
    private List<String> errors;
}
