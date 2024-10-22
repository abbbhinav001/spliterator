package com.example.spliterator.users.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Input DTO class for create user API.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserInputDto {
    @Schema(description = "First name of the user", example = "John")
    private String name;
}
