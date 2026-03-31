package com.restaurant.service.login;

import com.restaurant.dto.DtoNewToken;
import com.restaurant.security.AuthResponse;

public interface RefreshTokenService {

    public AuthResponse refreshToken(DtoNewToken dtoNewToken);

}
