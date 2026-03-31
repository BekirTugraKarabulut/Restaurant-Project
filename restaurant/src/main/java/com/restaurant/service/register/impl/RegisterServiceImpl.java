package com.restaurant.service.register.impl;

import com.restaurant.dto.DtoCustomer;
import com.restaurant.dto.RegisterCustomer;
import com.restaurant.exception.BaseException;
import com.restaurant.exception.ErrorMessage;
import com.restaurant.exception.MessageType;
import com.restaurant.model.Customer;
import com.restaurant.model.Role;
import com.restaurant.repository.CustomerRepository;
import com.restaurant.service.register.RegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterServiceImpl(CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public DtoCustomer register(RegisterCustomer registerCustomer) {

        Customer customer = new Customer();
        customer.setUsername(registerCustomer.getUsername());
        customer.setName(registerCustomer.getName());
        customer.setPassword(bCryptPasswordEncoder.encode(registerCustomer.getPassword()));
        customer.setRole(Role.CUSTOMER);

        if(customerRepository.findByUsername(customer.getUsername()).isPresent()){
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_ALREADY_USED , customer.getUsername()));
        }

        Customer dbCustomer = customerRepository.save(customer);
        DtoCustomer dtoCustomer = new DtoCustomer();
        BeanUtils.copyProperties(dbCustomer, dtoCustomer);

        return dtoCustomer;
    }

}
