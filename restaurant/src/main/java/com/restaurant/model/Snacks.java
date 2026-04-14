package com.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "snacks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Snacks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snack_id")
    private Long snackId;

    @Column(name = "snack_name")
    private String snackName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

}
