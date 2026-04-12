package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoAddress {

    private Long id;

    private String street;

    private String addressTitle;

    private DtoCustomer dtoCustomer;

}
