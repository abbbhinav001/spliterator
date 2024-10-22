package com.example.spliterator.restaurants.dao;

import com.example.spliterator.restaurants.models.RestaurantDetailsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantDao {
    /**
     * If the given ID is null, then fetch all the registered restaurants, else, fetch the restaurant with given ID.
     *
     * @param id Restaurant ID
     * @return List of {@link RestaurantDetailsModel}
     */
    List<RestaurantDetailsModel> fetchRestaurantDetails(Integer id);

    /**
     * Check if any restaurant with given name is already registered in the application.
     *
     * @param restaurantName Restaurant name
     * @return true - if restaurant exists <br> false - if restaurant doesn't exist
     */
    boolean checkRestaurantExistsByName(String restaurantName);

    /**
     * Registers the restaurant details in the application.
     *
     * @param restaurantDetails Restaurant details
     */
    void createRestaurant(RestaurantDetailsModel restaurantDetails);
}
