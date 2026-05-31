package com.restaurant.service.cart;

import com.restaurant.model.Cart;
import com.restaurant.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartDeleteByUsername {

    private final CartRepository cartRepository;

    public CartDeleteByUsername(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public boolean deleteCartByCartId(Long cartId) {

        Optional<Cart> cart = cartRepository.findByCartId(cartId);

        if(cart.isPresent()){
            cartRepository.delete(cart.get());
            return true;
        }else{
            return false;
        }

    }

}
