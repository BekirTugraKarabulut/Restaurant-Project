package com.restaurant.service.address.impl;

import com.restaurant.dto.DtoAddress;
import com.restaurant.dto.DtoAddressUI;
import com.restaurant.dto.DtoCustomer;
import com.restaurant.model.Address;
import com.restaurant.model.Customer;
import com.restaurant.repository.AddressRepository;
import com.restaurant.repository.CustomerRepository;
import com.restaurant.service.address.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public AddressServiceImpl(AddressRepository addressRepository , CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public DtoAddress saveAddress(DtoAddressUI dtoAddressUI) {

        Address address = new Address();
        address.setCity(dtoAddressUI.getCity());
        address.setStreet(dtoAddressUI.getStreet());

        Optional<Customer> customer = customerRepository.findByUsername(dtoAddressUI.getDtoCustomer().getUsername());
        address.setCustomer(customer.get());
        Address savedAddress = addressRepository.save(address);

        DtoAddress dtoAddress = new DtoAddress();
        BeanUtils.copyProperties(savedAddress, dtoAddress);

        DtoCustomer dtoCustomer = new DtoCustomer();
        BeanUtils.copyProperties(savedAddress.getCustomer(), dtoCustomer);
        dtoAddress.setDtoCustomer(dtoCustomer);

        return dtoAddress;

    }
}
