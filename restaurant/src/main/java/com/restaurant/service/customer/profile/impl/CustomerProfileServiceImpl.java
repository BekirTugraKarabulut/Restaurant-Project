package com.restaurant.service.customer.profile.impl;

import com.restaurant.dto.DtoAddress;
import com.restaurant.dto.DtoCustomer;
import com.restaurant.exception.BaseException;
import com.restaurant.exception.ErrorMessage;
import com.restaurant.exception.MessageType;
import com.restaurant.model.Address;
import com.restaurant.model.Customer;
import com.restaurant.repository.AddressByCustomerRepository;
import com.restaurant.repository.CustomerRepository;
import com.restaurant.service.customer.profile.CustomerProfileService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerRepository customerRepository;
    private final AddressByCustomerRepository addressByCustomerRepository;

    public CustomerProfileServiceImpl(CustomerRepository customerRepository , AddressByCustomerRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressByCustomerRepository = addressRepository;
    }

    @Override
    public DtoCustomer getCustomerProfile(String username) {

        Optional<Customer> customer = customerRepository.findByUsername(username);

        if(customer.isPresent()){
            DtoCustomer dtoCustomer = new DtoCustomer();
            dtoCustomer.setUsername(username);
            dtoCustomer.setName(customer.get().getName());
            dtoCustomer.setPassword(customer.get().getPassword());
            dtoCustomer.setPhoneNumber(String.valueOf(customer.get().getPhoneNumber()));
            dtoCustomer.setRole(customer.get().getRole());

            Optional<Address> address = addressByCustomerRepository.findByCustomer_Username(customer.get().getUsername());
            DtoAddress dtoAddress = new DtoAddress();
            dtoAddress.setId(address.get().getId());
            dtoAddress.setAddressTitle(address.get().getAddressTitle());
            dtoAddress.setStreet(address.get().getStreet());

            dtoCustomer.setDtoAddress(dtoAddress);

            return dtoCustomer;
        }else{
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_NOT_FOUND, username));
        }

    }

    @Override
    public DtoCustomer phoneNumberAdd(String username , String phoneNumber) {

        Optional<Customer> customer = customerRepository.findByUsername(username);

        if(customer.isPresent()){
            customer.get().setPhoneNumber(phoneNumber);
            Customer dbCustomer = customerRepository.save(customer.get());
            DtoCustomer dtoCustomer = new DtoCustomer();
            BeanUtils.copyProperties(dbCustomer, dtoCustomer);
            return dtoCustomer;
        }else{
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_NOT_FOUND, username));
        }
    }
}
