package com.example.spliterator.restaurants.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * Model class for restaurant details
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("RestaurantDetailsModel")
public class RestaurantDetailsModel {
    private int id;

    private String name;
}
