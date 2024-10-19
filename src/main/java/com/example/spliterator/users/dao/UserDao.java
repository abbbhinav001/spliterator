package com.example.spliterator.users.dao;

import com.example.spliterator.users.models.UserDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    /**
     * Insert a new user
     *
     * @param userDetails Contains details of the new user
     */
    void insertUser(UserDetails userDetails);

    /**
     * Fetch the user information along with their IDs.
     *
     * @param userId Optional, if userId is present then fetches the information of that particular user, else, fetches
     * the information of all the users
     * @return List of UserDetails
     */
    List<UserDetails> getUser(Integer userId);

    /**
     * Check whether a user exists with the given name or not.
     * @param name Name of the user
     * @return true, if the user exists, else, false.
     */
    boolean checkUserExistsByName(String name);
}
