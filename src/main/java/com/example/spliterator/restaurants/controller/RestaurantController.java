package com.example.spliterator.restaurants.controller;

import com.example.spliterator.common.dtos.ResponseModel;
import com.example.spliterator.common.model.ApiError;
import com.example.spliterator.restaurants.dtos.CreateRestaurantInputDto;
import com.example.spliterator.restaurants.dtos.RestaurantDetailsDataDto;
import com.example.spliterator.restaurants.dtos.RestaurantDetailsResponseDto;
import com.example.spliterator.restaurants.mapstruct.RestaurantMapper;
import com.example.spliterator.restaurants.service.RestaurantService;
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

    /**
     * This controller method is to fetch all the restaurants registered in the application.
     *
     * @return List of all restaurants of type {@link RestaurantDetailsResponseDto}
     */
    @Operation(tags = {"Restaurant"}, summary = "Fetch all the restaurants", description = "Fetch all the restaurants",
        responses = {@ApiResponse(responseCode = "200",
            content = @Content(schema = @Schema(implementation = RestaurantDetailsResponseDto.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Success Response."),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Bad Request"),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Internal Server Error")})
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

    /**
     * This controller method is to fetch a particular restaurant using its ID.
     *
     * @param restaurantId ID of the restaurant to be fetched.
     * @return Restaurant details for the given ID. Type {@link RestaurantDetailsResponseDto}
     */
    @Operation(tags = {"Restaurant"}, summary = "Fetch restaurant by ID",
        description = "Fetch restaurant for the given ID", parameters = {
        @Parameter(in = ParameterIn.PATH, name = "restaurantId", description = "ID of the restaurant to be fetched",
            required = true, example = "1")}, responses = {@ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = RestaurantDetailsResponseDto.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Success Response."),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Bad Request"),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Internal Server Error")})
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

    /**
     * This controller method is to register a new restaurant in the application.
     *
     * @param restaurantDetails Details of the new restaurant
     * @return Returns 201, if the request succeeds
     */
    @Operation(tags = {"Restaurant"}, summary = "Register Restaurant",
        description = "Register a new restaurant in the application", responses = {@ApiResponse(responseCode = "201",
        content = @Content(schema = @Schema(implementation = ResponseModel.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Success Response."),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Bad Request"),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Internal Server Error")})
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
