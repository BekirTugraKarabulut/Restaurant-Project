package com.restaurant.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    USERNAME_ALREADY_USED("1001" , "Username already used"),
    CUSTOMER_NOT_FOUND("1002" , "Customer not found"),
    REFRESH_TOKEN_NOT_FOUND("1003" , "Refresh token not found"),
    LOCAL_HOST_NOT_FOUND("1004" , "Local host not found"),
    CUSTOMER_ADDRESS_NOT_FOUND("1005" , "Customer address not found");

    private String code;
    private String message;

    MessageType(String code , String message) {
        this.code = code;
        this.message = message;
    }

}
