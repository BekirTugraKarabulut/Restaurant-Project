package com.restaurant.controller.address;

import com.restaurant.dto.DtoAddress;
import com.restaurant.dto.DtoAddressUI;

public interface AddressController {

    public DtoAddress saveAddress(DtoAddressUI dtoAddressUI);

}
