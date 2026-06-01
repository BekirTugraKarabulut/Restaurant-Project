package com.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pay")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long payId;

    @ManyToOne
    @JoinColumn(name = "username" , referencedColumnName = "username")
    private Customer customer;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private Integer price;

}
