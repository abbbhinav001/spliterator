package com.example.spliterator.foods.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodDetailsDto {
    @Schema(name = "id", description = "Food ID", example = "1")
    private Integer id;

    @Schema(name = "name", description = "Food name", example = "Fried Rice")
    private String name;

    @Schema(name = "restaurantId", description = "Restaurant ID", example = "2")
    private Integer restaurantId;

    @Schema(name = "price", description = "Price of the dish", example = "120.00")
    private Double price;
}
