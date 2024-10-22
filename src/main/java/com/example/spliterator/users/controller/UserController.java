package com.example.spliterator.users.controller;

import com.example.spliterator.common.dtos.ResponseModel;
import com.example.spliterator.common.model.ApiError;
import com.example.spliterator.users.dtos.CreateUserInputDto;
import com.example.spliterator.users.dtos.UserDetailsDataDto;
import com.example.spliterator.users.dtos.UserResponseDto;
import com.example.spliterator.users.mapstruct.UserMapper;
import com.example.spliterator.users.service.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    /**
     * API to fetch all registered users.
     *
     * @return List of all users including their user IDs.
     */
    @Operation(tags = {"User"}, summary = "Fetch all the users", description = "Fetch all the users", responses = {
        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserResponseDto.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Success Response."),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Bad Request"),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Internal Server Error")})
    @GetMapping
    public ResponseEntity<UserResponseDto> getAllUsers() {
        UserResponseDto response = UserResponseDto
            .builder()
            .code(HttpStatus.OK.value())
            .type(TYPE_SUCCESS)
            .msg("Fetched users successfully")
            .data(UserDetailsDataDto
                .builder()
                .items(userService.getAllUsers())
                .build())
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Fetch the details of the user using user ID. If no user is present, then return an empty list.
     *
     * @param userId ID of the user to be fetched
     * @return User corresponding to the provided user ID
     */
    @Operation(tags = {"User"}, summary = "Fetch user by ID",
        description = "Fetch a particular user using their user ID", parameters = {
        @Parameter(in = ParameterIn.PATH, name = "userId", description = "ID of the user to be fetched",
            required = true, example = "1")}, responses = {@ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = UserResponseDto.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Success Response."),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Bad Request"),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Internal Server Error")})
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userId") Integer userId) {
        UserResponseDto response = UserResponseDto
            .builder()
            .code(HttpStatus.OK.value())
            .type(TYPE_SUCCESS)
            .msg("Fetched user details successfully")
            .data(UserDetailsDataDto
                .builder()
                .items(userService.getUserById(userId))
                .build())
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Create a new user.
     *
     * @param userDetails Details of the user to be created
     * @return Returns 201, if the user is created
     */
    @Operation(tags = {"User"}, summary = "User creation", description = "Create a new user", responses = {
        @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ResponseModel.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Success Response."),
        @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Bad Request"),
        @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class),
            mediaType = MediaType.APPLICATION_JSON_VALUE), description = "Internal Server Error")})
    @PostMapping("create")
    public ResponseEntity<ResponseModel> createUser(@RequestBody CreateUserInputDto userDetails) {
        userService.createUser(userMapper.mapToModel(userDetails));

        ResponseModel response = ResponseModel
            .builder()
            .code(HttpStatus.CREATED.value())
            .type(TYPE_SUCCESS)
            .msg("User created successfully")
            .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
