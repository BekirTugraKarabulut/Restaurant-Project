package com.restaurant.controller.customer.profile;

import com.restaurant.dto.DtoCustomer;

public interface CustomerProfileController {

    public DtoCustomer getCustomerProfile(String username);

    public DtoCustomer phoneNumberAdd(String username , String phoneNumber);

}
