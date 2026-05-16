package com.restaurant.service.cart;

import com.restaurant.dto.DtoCart;
import com.restaurant.model.Cart;
import com.restaurant.model.Customer;
import com.restaurant.repository.CartRepository;
import com.restaurant.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartsByUsernameService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    public CartsByUsernameService(CartRepository cartRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    public List<DtoCart> getCartsByUsername(String username){

        Optional<Customer> customer = customerRepository.findByUsername(username);
        List<Cart> carts = cartRepository.findByCustomer_Username(customer.get().getUsername());

        if(carts.isEmpty()){
            throw new RuntimeException("No cart found for username " + customer.get().getUsername());
        }else{
            List<DtoCart> dtoCarts = carts.stream().map(cart -> {
                DtoCart dtoCart = new DtoCart();
                dtoCart.setUsername(customer.get().getUsername());
                dtoCart.setCartId(cart.getCartId());
                dtoCart.setProductId(cart.getProductId());
                dtoCart.setProductName(cart.getProductName());
                dtoCart.setPrice(cart.getPrice());
                dtoCart.setProductType(cart.getProductType());
                return dtoCart;
            }).toList();

            return dtoCarts;
        }

    }
}
