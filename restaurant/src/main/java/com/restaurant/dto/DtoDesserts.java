package com.restaurant.dto;

import com.restaurant.model.ProductType;
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

    private String imageUrl;

    private ProductType productType;

}
