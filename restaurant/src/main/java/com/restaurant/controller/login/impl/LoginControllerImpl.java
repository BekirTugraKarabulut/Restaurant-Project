package com.restaurant.controller.login.impl;

import com.restaurant.controller.login.LoginController;
import com.restaurant.security.AuthRequest;
import com.restaurant.security.AuthResponse;
import com.restaurant.service.login.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static com.restaurant.config.RestApis.*;

@RestController
@Tag(name = "Login Process" , description = "Login API Process")
public class LoginControllerImpl implements LoginController {

    private final LoginService loginService;

    public LoginControllerImpl(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(LOGIN)
    @Override
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return loginService.login(authRequest);
    }

}
