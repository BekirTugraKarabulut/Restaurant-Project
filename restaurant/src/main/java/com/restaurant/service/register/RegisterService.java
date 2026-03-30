package com.restaurant.service.register;

import com.restaurant.dto.DtoCustomer;
import com.restaurant.dto.RegisterCustomer;

public interface RegisterService {

    public DtoCustomer register(RegisterCustomer registerCustomer);

}
