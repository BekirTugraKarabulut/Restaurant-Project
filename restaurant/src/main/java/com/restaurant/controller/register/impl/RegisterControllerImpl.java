package com.restaurant.controller.register.impl;

import com.restaurant.controller.register.RegisterController;
import com.restaurant.dto.DtoCustomer;
import com.restaurant.dto.RegisterCustomer;
import com.restaurant.service.register.RegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@Tag(name = "RegisterController", description = "Controller for user registration")
public class RegisterControllerImpl implements RegisterController {

    private final RegisterService registerService;

    public RegisterControllerImpl(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping(path = "/register")
    @Override
    public DtoCustomer register(@Valid @RequestBody RegisterCustomer registerCustomer) {
        return registerService.register(registerCustomer);
    }

}
