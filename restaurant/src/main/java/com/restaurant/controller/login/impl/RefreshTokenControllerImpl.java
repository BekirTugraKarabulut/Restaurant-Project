package com.restaurant.controller.login.impl;

import com.restaurant.controller.login.RefreshTokenController;
import com.restaurant.dto.DtoNewToken;
import com.restaurant.security.AuthResponse;
import com.restaurant.service.login.RefreshTokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static com.restaurant.config.RestApis.*;

@RestController
@Tag(name = "Refresh Token Process" , description = "New Refresh Token PUT Process")
public class RefreshTokenControllerImpl implements RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    public RefreshTokenControllerImpl(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping(REFRESH)
    @Override
    public AuthResponse refreshToken(@RequestBody DtoNewToken dtoNewToken) {
        return refreshTokenService.refreshToken(dtoNewToken);
    }

}
