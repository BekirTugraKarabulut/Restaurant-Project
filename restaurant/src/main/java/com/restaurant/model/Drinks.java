package com.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drinks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_id")
    private Long drinkId;

    @Column(name = "drink_name")
    private String drinkName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

}
