package com.restaurant.controller.cart;

import com.restaurant.dto.DtoCart;
import com.restaurant.dto.DtoCartUI;
import com.restaurant.service.cart.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(path = "/saveCart")
    public DtoCart saveCart(@RequestBody DtoCartUI dtoCartUI){
        return cartService.saveCart(dtoCartUI);
    }

}
