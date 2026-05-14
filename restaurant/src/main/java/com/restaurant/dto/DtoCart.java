package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCart {

    private Long cartId;

    private String username;

    private Long productId;

    private String productName;

    private Integer price;

    private String productType;

}
