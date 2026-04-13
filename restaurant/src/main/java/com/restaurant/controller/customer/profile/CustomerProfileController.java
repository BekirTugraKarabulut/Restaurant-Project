package com.restaurant.controller.customer.profile;

import com.restaurant.dto.DtoCustomer;

public interface CustomerProfileController {

    public DtoCustomer getCustomerProfile(String username);

}
