package com.restaurant.service.address.impl;

import com.restaurant.dto.DtoAddress;
import com.restaurant.dto.DtoAddressUI;
import com.restaurant.dto.DtoCustomer;
import com.restaurant.exception.BaseException;
import com.restaurant.exception.ErrorMessage;
import com.restaurant.exception.MessageType;
import com.restaurant.model.Address;
import com.restaurant.model.Customer;
import com.restaurant.repository.AddressRepository;
import com.restaurant.repository.CustomerRepository;
import com.restaurant.service.address.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        address.setAddressTitle(dtoAddressUI.getAddressTitle());
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

    @Override
    public List<DtoAddress> getAddressByUsername(String username) {

        List<Address> addressList = addressRepository.findByCustomer_Username(username);
        List<DtoAddress> dtoAddressList = new ArrayList<>();

        if(addressList.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_ADDRESS_NOT_FOUND , "Customer with username " + username + " does not have any address"));
        }

        for (Address address : addressList.stream().toList()) {
            DtoAddress dtoAddress = new DtoAddress();
            BeanUtils.copyProperties(address, dtoAddress);

            DtoCustomer dtoCustomer = new DtoCustomer();
            BeanUtils.copyProperties(address.getCustomer(), dtoCustomer);
            dtoAddress.setDtoCustomer(dtoCustomer);

            dtoAddressList.add(dtoAddress);
        }

        return dtoAddressList;
    }
}
