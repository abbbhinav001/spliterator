package com.example.spliterator.users.mapstruct;

import com.example.spliterator.users.dtos.CreateUserInputDto;
import com.example.spliterator.users.dtos.UserDetailsDto;
import com.example.spliterator.users.models.UserDetails;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper to map models and DTOs in User class
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDetails mapToModel(CreateUserInputDto data);

    List<UserDetailsDto> mapToListDto(List<UserDetails> data);
}
