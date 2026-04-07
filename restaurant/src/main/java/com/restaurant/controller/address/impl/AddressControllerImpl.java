package com.restaurant.controller.address.impl;

import com.restaurant.controller.address.AddressController;
import com.restaurant.dto.DtoAddress;
import com.restaurant.dto.DtoAddressUI;
import com.restaurant.service.address.AddressService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressControllerImpl implements AddressController {

    private final AddressService addressService;

    public AddressControllerImpl(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    @PostMapping(path = "/saveAddress")
    public DtoAddress saveAddress(@RequestBody DtoAddressUI dtoAddressUI) {
        return addressService.saveAddress(dtoAddressUI);
    }

}
