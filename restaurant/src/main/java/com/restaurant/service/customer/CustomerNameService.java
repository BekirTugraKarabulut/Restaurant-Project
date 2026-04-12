package com.restaurant.service.customer;

import com.restaurant.exception.BaseException;
import com.restaurant.exception.ErrorMessage;
import com.restaurant.exception.MessageType;
import com.restaurant.model.Customer;
import com.restaurant.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerNameService {

    private final CustomerRepository customerRepository;

    public CustomerNameService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String customerName(String username) {

        Optional<Customer> customer = customerRepository.findByUsername(username);

        if(customer.isPresent()) {
            return customer.get().getName();
        } else {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_NOT_FOUND , "Customer Not Found"));
        }
    }

}
