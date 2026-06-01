package com.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCreditCart {

    private String cardNumber;

    private String mmyy;

    private String cvv;


}
