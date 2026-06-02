package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCourier {

    private Long courierId;

    private int time = 0;

    private DtoCustomer customer;

    private boolean delivered = false;

    private Integer price;

}
