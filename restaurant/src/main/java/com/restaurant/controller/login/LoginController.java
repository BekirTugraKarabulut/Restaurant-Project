package com.restaurant.controller.login;

import com.restaurant.security.AuthRequest;
import com.restaurant.security.AuthResponse;

public interface LoginController {

    public AuthResponse login(AuthRequest authRequest);

}
