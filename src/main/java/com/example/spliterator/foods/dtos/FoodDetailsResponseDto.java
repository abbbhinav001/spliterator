package com.example.spliterator.foods.dtos;

import com.example.spliterator.common.dtos.ResponseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FoodDetailsResponseDto extends ResponseModel {
    @Schema(name = "data", description = "Data model", implementation = FoodDetailsDataDto.class)
    private FoodDetailsDataDto data;
}
