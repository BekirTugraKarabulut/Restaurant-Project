package com.restaurant.controller.customer.profile.impl;

import com.restaurant.controller.customer.profile.CustomerProfileController;
import com.restaurant.dto.DtoCustomer;
import com.restaurant.service.customer.profile.CustomerProfileService;
import org.springframework.web.bind.annotation.*;

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

    @Override
    @PutMapping(path = "/profile/phoneNumber/{username}")
    public DtoCustomer phoneNumberAdd(@PathVariable(name = "username" , required = true) String username,@RequestBody String phoneNumber) {
        return customerProfileService.phoneNumberAdd(username, phoneNumber);
    }

}
