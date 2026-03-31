package com.restaurant.service.login;

import com.restaurant.security.AuthRequest;
import com.restaurant.security.AuthResponse;

public interface LoginService {

    public AuthResponse login(AuthRequest authRequest);

}
