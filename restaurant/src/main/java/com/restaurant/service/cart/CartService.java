package com.restaurant.service.cart;

import com.restaurant.dto.DtoCart;
import com.restaurant.dto.DtoCartUI;
import com.restaurant.model.Cart;
import com.restaurant.model.Customer;
import com.restaurant.model.Foods;
import com.restaurant.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final DessertsRepository dessertsRepository;
    private final FoodsRepository foodsRepository;
    private final DrinksRepository drinksRepository;
    private final SnacksRepository snacksRepository;
    private final CustomerRepository customerRepository;

    public CartService(CartRepository cartRepository, DessertsRepository dessertsRepository, FoodsRepository foodsRepository, DrinksRepository drinksRepository, SnacksRepository snacksRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.dessertsRepository = dessertsRepository;
        this.foodsRepository = foodsRepository;
        this.drinksRepository = drinksRepository;
        this.snacksRepository = snacksRepository;
        this.customerRepository = customerRepository;
    }

    public DtoCart saveCart(DtoCartUI dtoCartUI) {

        Cart cart = new Cart();
        cart.setProductType(dtoCartUI.getProductType());
        cart.setProductId(dtoCartUI.getProductId());

        if(cart.getProductType().equals("FOOD")){

            Optional<Foods> foodsOptional = foodsRepository.findByFoodId(cart.getProductId());

            if(foodsOptional.isPresent()){

                cart.setPrice(foodsOptional.get().getPrice());
                cart.setProductName(foodsOptional.get().getFoodName());

                Optional<Customer> customer = customerRepository.findByUsername(dtoCartUI.getUsername());
                cart.setCustomer(customer.get());
                Cart savedCart = cartRepository.save(cart);

                DtoCart dtoCart = new DtoCart();
                BeanUtils.copyProperties(savedCart, dtoCart);
                dtoCart.setUsername(savedCart.getCustomer().getUsername());
                return dtoCart;

            }
        } else if (cart.getProductType().equals("DRINK")){

        } else if (cart.getProductType().equals("DESSERT")) {

        } else if (cart.getProductType().equals("SNACK")) {

        }

        return null;
    }

}
