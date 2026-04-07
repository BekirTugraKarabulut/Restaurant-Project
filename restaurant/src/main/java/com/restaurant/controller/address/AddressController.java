package com.restaurant.controller.address;

import com.restaurant.dto.DtoAddress;
import com.restaurant.dto.DtoAddressUI;

import java.util.List;

public interface AddressController {

    public DtoAddress saveAddress(DtoAddressUI dtoAddressUI);

    public List<DtoAddress> getAddressByUsername(String username);

}
