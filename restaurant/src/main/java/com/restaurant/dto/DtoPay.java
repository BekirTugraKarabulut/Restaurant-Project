package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoPay {

    private Long payId;

    private DtoCustomer customer;

    private String address;

    private Integer price;

}
