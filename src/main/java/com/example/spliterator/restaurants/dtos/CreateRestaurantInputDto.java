package com.example.spliterator.restaurants.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Input DTO for Create restaurant API.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantInputDto {
    @Schema(description = "Name of the restaurant", example = "Ice Cream Factory")
    private String name;
}
