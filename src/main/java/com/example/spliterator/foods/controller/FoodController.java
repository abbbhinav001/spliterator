package com.example.spliterator.foods.controller;

import com.example.spliterator.common.dtos.ResponseModel;
import com.example.spliterator.common.model.ApiError;
import com.example.spliterator.foods.dtos.FoodDetailsDataDto;
import com.example.spliterator.foods.dtos.FoodDetailsResponseDto;
import com.example.spliterator.foods.dtos.NewFoodDetailsDto;
import com.example.spliterator.foods.mapstruct.FoodMapper;
import com.example.spliterator.foods.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.spliterator.common.constants.MessageType.TYPE_SUCCESS;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    private final FoodMapper foodMapper;

    /**
     * This controller method is to fetch the food items that are available in the given restaurant.
     *
     * @param restaurantId Restaurant ID
     * @return List of Food items with their corresponding details
     */
    @Operation(tags = {"Food"}, summary = "Fetch foods for given restaurant",
        description = "Fetch details of the foods available in the given restaurant", parameters = {
        @Parameter(in = ParameterIn.QUERY, name = "restaurantId",
            description = "ID of the restaurant from which the food details are fetched", example = "2")}, responses = {
        @ApiResponse(responseCode = "200",
            content = @Content(schema = @Schema(implementation = FoodDetailsResponseDto.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Success Response."),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Bad Request"),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Internal Server Error")})
    @GetMapping
    public ResponseEntity<FoodDetailsResponseDto> getFoodForGivenRestaurant(
        @RequestParam("restaurantId") Integer restaurantId) {
        FoodDetailsResponseDto response = FoodDetailsResponseDto
            .builder()
            .code(HttpStatus.OK.value())
            .type(TYPE_SUCCESS)
            .msg("Food for corresponding restaurant fetched successfully")
            .data(FoodDetailsDataDto
                .builder()
                .items(foodService.fetchFoodListForGivenRestaurant(restaurantId))
                .build())
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This controller method is to enter the details of the new food item to be added to the corresponding restaurant.
     *
     * @param newFoodDetails Food Details
     * @return Returns 201 Created, if API is successful.
     */
    @Operation(tags = {"Food"}, summary = "Enter new food details",
        description = "Create new food entry for the given restaurant", parameters = {
        @Parameter(in = ParameterIn.QUERY, name = "restaurantId",
            description = "ID of the restaurant from which the food details are fetched", example = "2")}, responses = {
        @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ResponseModel.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Success Response."),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Bad Request"),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Internal Server Error")})
    @PostMapping
    public ResponseEntity<ResponseModel> enterNewFoodDetails(@RequestBody NewFoodDetailsDto newFoodDetails) {
        foodService.enterNewFoodDetails(foodMapper.mapToModel(newFoodDetails));

        ResponseModel response = ResponseModel
            .builder()
            .code(HttpStatus.CREATED.value())
            .type(TYPE_SUCCESS)
            .msg("Entered New Food details successfully")
            .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
