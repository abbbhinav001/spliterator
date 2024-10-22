package com.example.spliterator.restaurants.mapstruct;

import com.example.spliterator.restaurants.dtos.CreateRestaurantInputDto;
import com.example.spliterator.restaurants.dtos.RestaurantDetails;
import com.example.spliterator.restaurants.models.RestaurantDetailsModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDetailsModel mapToModel(CreateRestaurantInputDto data);

    List<RestaurantDetails> mapToDto(List<RestaurantDetailsModel> data);
}
