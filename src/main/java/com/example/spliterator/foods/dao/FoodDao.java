package com.example.spliterator.foods.dao;

import com.example.spliterator.foods.models.FoodDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodDao {
    List<FoodDetails> fetchFoodsForGivenRestaurant(Integer restaurantId);

    void enterNewFoodDetails(FoodDetails foodDetails);

    boolean checkFoodNameAlreadyExistsForGivenRestaurant(String foodName, Integer restaurantId);
}
