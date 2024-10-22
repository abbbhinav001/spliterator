package com.example.spliterator.users.controller;

import com.example.spliterator.common.dtos.ResponseModel;
import com.example.spliterator.users.dtos.CreateUserInputDto;
import com.example.spliterator.users.dtos.UserResponseDto;
import com.example.spliterator.users.dtos.UserDetailsDataDto;
import com.example.spliterator.users.dtos.UserDetailsDto;
import com.example.spliterator.users.mapstruct.UserMapper;
import com.example.spliterator.users.service.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

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

    @PostMapping("create")
    public ResponseEntity<ResponseModel> createUser(@RequestBody CreateUserInputDto userDetails) {
        userService.createUser(userMapper.mapToModel(userDetails));

        ResponseModel response = ResponseModel
            .builder()
            .code(HttpStatus.OK.value())
            .type(TYPE_SUCCESS)
            .msg("User created successfully")
            .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
