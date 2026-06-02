package com.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courier")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    private Long courierId;

    @Column(name = "time")
    private int time = 0;

    @ManyToOne
    @JoinColumn(
            name = "username",
            referencedColumnName = "username")
    private Customer customer;

    @Column(name = "delivered")
    private boolean delivered = false;

    @Column(name = "price")
    private Integer price;

}
