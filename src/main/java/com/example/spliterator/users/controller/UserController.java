package com.example.spliterator.users.controller;

import com.example.spliterator.users.dtos.UserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class UserController {

    @PostMapping("create")
    public ResponseEntity createUser(@RequestBody UserDetails userDetails) {
        // insert new user into the database
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
