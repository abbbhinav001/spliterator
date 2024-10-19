package com.example.spliterator.users.dtos;

import com.example.spliterator.common.dtos.ResponseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto extends ResponseModel {
    @Schema(description = "List of all users and their corresponding IDs")
    private UserDetailsDataDto data;
}
