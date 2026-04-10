package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoAddDesserts {

    private Long dessertId;

    private String dessertName;

    private String description;

    private Integer price;

}
