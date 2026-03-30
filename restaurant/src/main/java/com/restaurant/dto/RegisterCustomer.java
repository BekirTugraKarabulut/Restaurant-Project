package com.restaurant.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCustomer {

    @Email(message = "Invalid email format")
    private String username;

    private String name;

    private String password;

}
