package com.restaurant.service.customer.profile;

import com.restaurant.dto.DtoCustomer;

public interface CustomerProfileService {

    public DtoCustomer getCustomerProfile(String username);

    public DtoCustomer phoneNumberAdd(String username , String phoneNumber);

}
