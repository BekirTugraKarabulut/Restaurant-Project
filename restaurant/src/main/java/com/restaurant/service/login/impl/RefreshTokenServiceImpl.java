package com.restaurant.service.login.impl;

import com.restaurant.dto.DtoNewToken;
import com.restaurant.exception.BaseException;
import com.restaurant.exception.ErrorMessage;
import com.restaurant.exception.MessageType;
import com.restaurant.model.Customer;
import com.restaurant.model.RefreshToken;
import com.restaurant.repository.RefreshTokenRepository;
import com.restaurant.security.AuthResponse;
import com.restaurant.security.JwtService;
import com.restaurant.service.login.RefreshTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, JwtService jwtService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }

    public RefreshToken createRefreshToken(Customer customer) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCustomer(customer);
        refreshToken.setIssuedAt(new Date());
        refreshToken.setExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        refreshToken.setToken(UUID.randomUUID().toString());
        return refreshToken;
    }

    public boolean isValidToken(Date expiredDate){
        return new Date().before(expiredDate);
    }

    @Override
    public AuthResponse refreshToken(DtoNewToken dtoNewToken) {

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(dtoNewToken.getToken());

        if(refreshToken.isPresent() && isValidToken(refreshToken.get().getExpiresAt())) {
            String accessToken = jwtService.generateToken(refreshToken.get().getCustomer());
            RefreshToken refreshNewToken = createRefreshToken(refreshToken.get().getCustomer());
            RefreshToken dbRefreshToken = refreshTokenRepository.save(refreshNewToken);
            return new AuthResponse(accessToken , dbRefreshToken.getToken());
        }else{
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND , "Refresh token not found"));
        }

    }
}
