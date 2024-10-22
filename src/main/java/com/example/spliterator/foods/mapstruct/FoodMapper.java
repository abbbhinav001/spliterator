package com.example.spliterator.foods.mapstruct;

import com.example.spliterator.foods.dtos.FoodDetailsDto;
import com.example.spliterator.foods.dtos.NewFoodDetailsDto;
import com.example.spliterator.foods.models.FoodDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    List<FoodDetailsDto> mapToDto(List<FoodDetails> data);

    FoodDetails mapToModel(NewFoodDetailsDto data);
}
