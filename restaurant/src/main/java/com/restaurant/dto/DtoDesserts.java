package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoDesserts implements Serializable {

    private Long dessertId;

    private String dessertName;

    private String description;

    private Integer price;

}
