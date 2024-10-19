package com.example.spliterator.users.models;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Builder
@Alias("UserDetails")
public class UserDetails {
    private int id;

    private String name;
}
