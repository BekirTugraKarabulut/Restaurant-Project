package com.restaurant.service.cart;

import com.restaurant.dto.DtoCart;
import org.springframework.stereotype.Service;

@Service
public class CartPricingService {

    private final CartsByUsernameService cartsByUsernameService;

    public CartPricingService(CartsByUsernameService cartsByUsernameService) {
        this.cartsByUsernameService = cartsByUsernameService;
    }

    public Integer calculateTotalPrice(String username) {
        return cartsByUsernameService.getCartsByUsername(username)
                .stream()
                .map(DtoCart::getPrice)
                .reduce(0 , Integer::sum);
    }

}
