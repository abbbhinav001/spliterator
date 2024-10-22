package com.example.spliterator.restaurants.dao;

import com.example.spliterator.restaurants.models.RestaurantDetailsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantDao {
    List<RestaurantDetailsModel> fetchRestaurants(Integer id);

    boolean checkRestaurantExistsByName(String restaurantName);

    void createRestaurant(RestaurantDetailsModel restaurantDetails);
}
