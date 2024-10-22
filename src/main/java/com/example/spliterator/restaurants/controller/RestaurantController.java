package com.example.spliterator.restaurants.controller;

import com.example.spliterator.common.dtos.ResponseModel;
import com.example.spliterator.restaurants.dtos.CreateRestaurantInputDto;
import com.example.spliterator.restaurants.dtos.RestaurantDetailsDataDto;
import com.example.spliterator.restaurants.dtos.RestaurantDetailsResponseDto;
import com.example.spliterator.restaurants.mapstruct.RestaurantMapper;
import com.example.spliterator.restaurants.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.spliterator.common.constants.MessageType.TYPE_SUCCESS;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    private final RestaurantMapper restaurantMapper;

    @GetMapping
    public ResponseEntity<RestaurantDetailsResponseDto> fetchAllRestaurants() {
        RestaurantDetailsResponseDto response = RestaurantDetailsResponseDto
            .builder()
            .code(HttpStatus.OK.value())
            .type(TYPE_SUCCESS)
            .msg("Restaurants fetched successfully")
            .data(RestaurantDetailsDataDto
                .builder()
                .items(restaurantService.getAllRestaurants())
                .build())
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDetailsResponseDto> fetchRestaurantById(
        @PathVariable("restaurantId") Integer restaurantId) {
        RestaurantDetailsResponseDto response = RestaurantDetailsResponseDto
            .builder()
            .code(HttpStatus.OK.value())
            .type(TYPE_SUCCESS)
            .msg("Restaurant fetched successfully")
            .data(RestaurantDetailsDataDto
                .builder()
                .items(restaurantService.getRestaurantById(restaurantId))
                .build())
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseModel> createRestaurant(@RequestBody CreateRestaurantInputDto restaurantDetails) {
        restaurantService.createRestaurant(restaurantMapper.mapToModel(restaurantDetails));

        ResponseModel response = ResponseModel
            .builder()
            .code(HttpStatus.CREATED.value())
            .type(TYPE_SUCCESS)
            .msg("Restaurant created successfully")
            .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
