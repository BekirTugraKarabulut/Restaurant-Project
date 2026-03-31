package com.restaurant.controller.login;

import com.restaurant.dto.DtoNewToken;
import com.restaurant.security.AuthResponse;

public interface RefreshTokenController {

    public AuthResponse refreshToken(DtoNewToken dtoNewToken);

}
