package com.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "desserts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Desserts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dessert_id")
    private Long dessertId;

    @Column(name = "dessert_name")
    private String dessertName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;

}
