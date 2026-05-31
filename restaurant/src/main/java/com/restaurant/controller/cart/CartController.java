package com.restaurant.controller.cart;

import com.restaurant.dto.DtoCart;
import com.restaurant.dto.DtoCartUI;
import com.restaurant.service.cart.CartAddService;
import com.restaurant.service.cart.CartDeleteByUsername;
import com.restaurant.service.cart.CartPricingService;
import com.restaurant.service.cart.CartsByUsernameService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cart")
@Tag(name = "Cart Controller", description = "Controller for handling cart operations")
public class CartController {

    private final CartAddService cartAddService;
    private final CartsByUsernameService cartsByUsernameService;
    private final CartPricingService cartPricingService;
    private final CartDeleteByUsername cartDeleteByUsername;

    public CartController(CartAddService cartAddService, CartsByUsernameService cartsByUsernameService, CartPricingService cartPricingService, CartDeleteByUsername cartDeleteByUsername) {
        this.cartAddService = cartAddService;
        this.cartsByUsernameService = cartsByUsernameService;
        this.cartPricingService = cartPricingService;
        this.cartDeleteByUsername = cartDeleteByUsername;
    }

    @PostMapping(path = "/saveCart")
    public DtoCart saveCart(@RequestBody DtoCartUI dtoCartUI){
        return cartAddService.saveCart(dtoCartUI);
    }

    @GetMapping(path = "/getCartsByUsername/{username}")
    public List<DtoCart> getCartsByUsername(@PathVariable(name = "username" ,required = true) String username){
        return cartsByUsernameService.getCartsByUsername(username);
    }

    @GetMapping(path = "/calculateTotalPrice/{username}")
    public Integer calculateTotalPrice(@PathVariable(name = "username" , required = true) String username){
        return cartPricingService.calculateTotalPrice(username);
    }

    @DeleteMapping(path = "/deleteCartByCartId/{cartId}")
    public boolean deleteCartByCartId(@PathVariable(name = "cartId" , required = true) Long cartId){
        return cartDeleteByUsername.deleteCartByCartId(cartId);
    }

}
