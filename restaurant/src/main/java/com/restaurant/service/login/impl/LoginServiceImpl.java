package com.restaurant.service.login.impl;

import com.restaurant.exception.BaseException;
import com.restaurant.exception.ErrorMessage;
import com.restaurant.exception.MessageType;
import com.restaurant.model.Customer;
import com.restaurant.model.RefreshToken;
import com.restaurant.repository.CustomerRepository;
import com.restaurant.repository.RefreshTokenRepository;
import com.restaurant.security.AuthRequest;
import com.restaurant.security.AuthResponse;
import com.restaurant.security.JwtService;
import com.restaurant.service.login.LoginService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {


    private final CustomerRepository customerRepository;
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    public LoginServiceImpl(CustomerRepository customerRepository, AuthenticationProvider authenticationProvider, JwtService jwtService, RefreshTokenRepository refreshTokenRepository) {
        this.customerRepository = customerRepository;
        this.authenticationProvider = authenticationProvider;
        this.jwtService = jwtService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createRefreshToken(Customer customer) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCustomer(customer);
        refreshToken.setIssuedAt(new Date());
        refreshToken.setExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        refreshToken.setToken(UUID.randomUUID().toString());
        return refreshToken;
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        authenticationProvider.authenticate(authenticationToken);

        Optional<Customer> customer = customerRepository.findByUsername(authRequest.getUsername());

        if(customer.isPresent()) {
            String accessToken = jwtService.generateToken(customer.get());
            RefreshToken refreshToken = createRefreshToken(customer.get());
            RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
            return new AuthResponse(accessToken , savedRefreshToken.getToken());
        }else{
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_NOT_FOUND , authRequest.getUsername()));
        }

    }
}
