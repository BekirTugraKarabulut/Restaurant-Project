package com.restaurant.service.address;

import com.restaurant.dto.DtoAddress;
import com.restaurant.dto.DtoAddressUI;

public interface AddressService {

    public DtoAddress saveAddress(DtoAddressUI dtoAddressUI);

}
