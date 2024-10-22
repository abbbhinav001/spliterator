package com.example.spliterator.restaurants.service;

import com.example.spliterator.common.exception.exceptions.BadRequestException;
import com.example.spliterator.restaurants.dao.RestaurantDao;
import com.example.spliterator.restaurants.dtos.RestaurantDetails;
import com.example.spliterator.restaurants.mapstruct.RestaurantMapper;
import com.example.spliterator.restaurants.models.RestaurantDetailsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.spliterator.common.constants.MessageType.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantDao restaurantDao;

    private final RestaurantMapper restaurantMapper;

    /**
     * Service method to fetch all the restaurants.
     *
     * @return List of type {@link RestaurantDetails}
     */
    public List<RestaurantDetails> getAllRestaurants() {
        List<RestaurantDetailsModel> restaurantDetails = restaurantDao.fetchRestaurants(null);
        return restaurantMapper.mapToDto(restaurantDetails);
    }

    /**
     * Service method to fetch restaurant details using ID.
     *
     * @param id ID of the restaurant to be fetched
     * @return Type {@link RestaurantDetails}
     */
    public List<RestaurantDetails> getRestaurantById(Integer id) {
        List<RestaurantDetailsModel> restaurantDetails = restaurantDao.fetchRestaurants(id);
        return restaurantMapper.mapToDto(restaurantDetails);
    }

    /**
     * Service method to register a new restaurant into the application
     *
     * @param restaurantDetails Details of the new restaurant to be registered.
     */
    public void createRestaurant(RestaurantDetailsModel restaurantDetails) {
        boolean checkRestaurantExists = restaurantDao.checkRestaurantExistsByName(restaurantDetails.getName());
        if (checkRestaurantExists) {
            throw new BadRequestException(BAD_REQUEST, List.of("Restaurant name already exists"));
        }
        restaurantDao.createRestaurant(restaurantDetails);

    }
}
