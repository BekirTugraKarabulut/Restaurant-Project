package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoFoods implements Serializable {

    private Long foodId;

    private String foodName;

    private String description;

    private Integer price;

}
