package com.restaurant.service.address;

import com.restaurant.dto.DtoAddress;
import com.restaurant.dto.DtoAddressUI;

import java.util.List;

public interface AddressService {

    public DtoAddress saveAddress(DtoAddressUI dtoAddressUI);

    public List<DtoAddress> getAddressByUsername(String username);

    public String getAddressName(String username);

}
