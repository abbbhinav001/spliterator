package com.example.spliterator.restaurants.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDetails {
    @Schema(description = "ID of the restaurant", example = "1")
    private int id;

    @Schema(description = "Name of the restaurant", example = "Ice Cream Factory")
    private String name;
}
