package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoAddDrinks {

    private String drinkName;

    private String description;

    private Integer price;

    private String imageUrl;

}
