package com.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credit_cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCart {

    @Id
    @Column(name = "credit_cart")
    private String cardNumber;

    @Column(name = "mm/yy")
    private String mmyy;

    @Column(name = "cvv" , unique = true)
    private String cvv;

}
