package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoDrinks implements Serializable {

    private Long drinkId;

    private String drinkName;

    private String description;

    private Integer price;

    private String imageUrl;

}
