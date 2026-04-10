package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoDrinks {

    private Long drinkId;

    private String drinkName;

    private String description;

    private Integer price;

}
