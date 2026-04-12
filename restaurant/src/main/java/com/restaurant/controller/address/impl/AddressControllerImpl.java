package com.restaurant.controller.address.impl;

import com.restaurant.controller.address.AddressController;
import com.restaurant.dto.DtoAddress;
import com.restaurant.dto.DtoAddressUI;
import com.restaurant.service.address.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/address")
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

    @Override
    @GetMapping(path = "/getAddressByUsername/{username}")
    public List<DtoAddress> getAddressByUsername(@PathVariable(name = "username" , required = true) String username) {
        return addressService.getAddressByUsername(username);
    }

    @Override
    @GetMapping(path = "/getAddressName/{username}")
    public String getAddressName(@PathVariable(name = "username" , required = true) String username) {
        return addressService.getAddressName(username);
    }

}
