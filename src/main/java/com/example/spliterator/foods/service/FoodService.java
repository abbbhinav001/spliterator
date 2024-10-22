package com.example.spliterator.foods.service;

import com.example.spliterator.common.exception.exceptions.BadRequestException;
import com.example.spliterator.foods.dao.FoodDao;
import com.example.spliterator.foods.dtos.FoodDetailsDto;
import com.example.spliterator.foods.mapstruct.FoodMapper;
import com.example.spliterator.foods.models.FoodDetails;
import com.example.spliterator.restaurants.dao.RestaurantDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodDao foodDao;

    private final RestaurantDao restaurantDao;

    private final FoodMapper foodMapper;

    /**
     * Method to fetch the list of food available in the given restaurant.
     *
     * @param restaurantId Restaurant ID
     * @return List of {@link FoodDetailsDto}
     */
    public List<FoodDetailsDto> fetchFoodListForGivenRestaurant(Integer restaurantId) {
        List<FoodDetails> foodDetailsList = foodDao.fetchFoodsForGivenRestaurant(restaurantId);
        return foodMapper.mapToDto(foodDetailsList);
    }

    /**
     * Method to register new food item for the given restaurant.
     *
     * @param foodDetails Food Details
     */
    public void enterNewFoodDetails(FoodDetails foodDetails) {
        List<String> inputErrors = new ArrayList<>();

        validateRestaurantDetails(foodDetails.getRestaurantId(), inputErrors);
        validateFoodDetails(foodDetails, inputErrors);

        if (!inputErrors.isEmpty()) {
            throw new BadRequestException(BAD_REQUEST.getReasonPhrase(), inputErrors);
        }
        foodDao.enterNewFoodDetails(foodDetails);
    }

    private void validateRestaurantDetails(Integer restaurantId, List<String> inputErrors) {
        var restaurantDetails = restaurantDao.fetchRestaurantDetails(restaurantId);
        if (restaurantDetails.isEmpty()) {
            inputErrors.add("Invalid restaurant ID");
        }
    }

    private void validateFoodDetails(FoodDetails foodDetails, List<String> inputErrors) {
        var checkFoodAlreadyExists =
            foodDao.checkFoodNameAlreadyExistsForGivenRestaurant(foodDetails.getName(), foodDetails.getRestaurantId());
        if (checkFoodAlreadyExists) {
            inputErrors.add("This item already exists. No need to add as new item.");
        }
    }
}
