package com.restaurant.dto;

import com.restaurant.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCustomer {

    private String username;

    private String name;

    private String password;

    private String phoneNumber;

    private DtoAddress dtoAddress;

    private Role role;

}
