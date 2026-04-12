package com.restaurant.controller.customer;

import com.restaurant.service.customer.CustomerNameService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
@Tag(name = "Customer Name Controller", description = "Controller for retrieving customer names based on their username")
public class CustomerNameController {

    private final CustomerNameService customerNameService;

    public CustomerNameController(CustomerNameService customerNameService) {
        this.customerNameService = customerNameService;
    }

    @GetMapping(path = "/name/{username}")
    public String customerName(@PathVariable(name = "username" , required = true) String username) {
            return customerNameService.customerName(username);
    }

}
