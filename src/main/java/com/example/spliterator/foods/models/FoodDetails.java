package com.example.spliterator.foods.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("FoodDetails")
public class FoodDetails {
    private Integer id;

    private String name;

    private Integer restaurantId;

    private Double price;
}
