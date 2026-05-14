package com.restaurant.model;

import lombok.Getter;

@Getter
public enum ProductType {

    FOOD("food"),
    DRINK("drink"),
    SNACK("snack"),
    DESSERT("dessert");

    private final String type;

    ProductType(String type) {
        this.type = type;
    }

}
