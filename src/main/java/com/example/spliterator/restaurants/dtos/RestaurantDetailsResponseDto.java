package com.example.spliterator.restaurants.dtos;

import com.example.spliterator.common.dtos.ResponseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RestaurantDetailsResponseDto extends ResponseModel {
    private RestaurantDetailsDataDto data;
}
