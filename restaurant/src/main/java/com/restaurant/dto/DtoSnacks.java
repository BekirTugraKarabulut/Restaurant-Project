package com.restaurant.dto;

import com.restaurant.model.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoSnacks implements Serializable {

    private Long snackId;

    private String snackName;

    private String description;

    private Integer price;

    private String imageUrl;

    private ProductType productType;

}
