package com.example.spliterator.foods.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodDetailsDataDto {
    @Schema(name = "data", description = "List of food items")
    private List<FoodDetailsDto> items;
}
