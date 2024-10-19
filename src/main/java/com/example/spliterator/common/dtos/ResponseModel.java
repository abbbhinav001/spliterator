package com.example.spliterator.common.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Basic response model
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {
    @Schema(description = "Status code", example = "200")
    private int code;

    @Schema(description = "Response type", example = "SUCCESS")
    private String type;

    @Schema(description = "Response message", example = "Operation completed successfully")
    private String msg;
}
