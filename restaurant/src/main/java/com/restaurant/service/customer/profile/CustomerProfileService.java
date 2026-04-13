package com.restaurant.service.customer.profile;

import com.restaurant.dto.DtoCustomer;

public interface CustomerProfileService {

    public DtoCustomer getCustomerProfile(String username);

}
