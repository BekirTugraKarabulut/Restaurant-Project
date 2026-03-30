package com.restaurant.model;

public enum Role {

    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");

    private String role;

    Role(String role) {
        this.role = role;
    }

}
