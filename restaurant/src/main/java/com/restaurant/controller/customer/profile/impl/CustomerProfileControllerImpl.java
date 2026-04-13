package com.restaurant.controller.customer.profile.impl;

import com.restaurant.controller.customer.profile.CustomerProfileController;
import com.restaurant.dto.DtoCustomer;
import com.restaurant.service.customer.profile.CustomerProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
public class CustomerProfileControllerImpl implements CustomerProfileController {

    private final CustomerProfileService customerProfileService;

    public CustomerProfileControllerImpl(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }


    @Override
    @GetMapping(path = "/profile/{username}")
    public DtoCustomer getCustomerProfile(@PathVariable(name = "username" , required = true) String username) {
        return customerProfileService.getCustomerProfile(username);
    }

}
