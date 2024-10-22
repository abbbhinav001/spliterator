package com.example.spliterator.users.service;

import com.example.spliterator.common.exception.exceptions.BadRequestException;
import com.example.spliterator.users.dao.UserDao;
import com.example.spliterator.users.dtos.UserDetailsDto;
import com.example.spliterator.users.mapstruct.UserMapper;
import com.example.spliterator.users.models.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.spliterator.common.constants.MessageType.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    private final UserMapper userMapper;

    /**
     * Get All existing users
     *
     * @return List of users with their IDs
     */
    public List<UserDetailsDto> getAllUsers() {
        List<UserDetails> users = userDao.getUser(null);
        return userMapper.mapToListDto(users);
    }

    /**
     * Get a particular user by ID
     *
     * @param userId User ID
     * @return User details of the given ID
     */
    public List<UserDetailsDto> getUserById(Integer userId) {
        List<UserDetails> users = userDao.getUser(userId);
        return userMapper.mapToListDto(users);
    }

    /**
     * Creates user with given details after validating the data.
     *
     * @param userDetails User details
     */
    public void createUser(UserDetails userDetails) {
        List<String> errors = new ArrayList<>();

        if (!userDetails
            .getName()
            .matches("^[a-zA-Z]*$")) {
            errors.add("Invalid name. Enter first name only.");
        }

        boolean checkIfUserExists = userDao.checkUserExistsByName(userDetails.getName());
        if (!checkIfUserExists && errors.isEmpty()) {
            userDao.insertUser(userDetails);
        } else {
            errors.add("User already exists.");
            throw new BadRequestException(BAD_REQUEST, errors);
        }
    }
}
