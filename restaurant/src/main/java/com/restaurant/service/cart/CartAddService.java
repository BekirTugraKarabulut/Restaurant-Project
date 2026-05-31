package com.restaurant.service.cart;

import com.restaurant.dto.DtoCart;
import com.restaurant.dto.DtoCartUI;
import com.restaurant.model.*;
import com.restaurant.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartAddService {

    private final CartRepository cartRepository;
    private final DessertsRepository dessertsRepository;
    private final FoodsRepository foodsRepository;
    private final DrinksRepository drinksRepository;
    private final SnacksRepository snacksRepository;
    private final CustomerRepository customerRepository;

    public CartAddService(CartRepository cartRepository, DessertsRepository dessertsRepository, FoodsRepository foodsRepository, DrinksRepository drinksRepository, SnacksRepository snacksRepository, CustomerRepository customerRepository) {
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
                cart.setImageUrl(foodsOptional.get().getImageUrl());

                Optional<Customer> customer = customerRepository.findByUsername(dtoCartUI.getUsername());
                cart.setCustomer(customer.get());
                Cart savedCart = cartRepository.save(cart);

                DtoCart dtoCart = new DtoCart();
                BeanUtils.copyProperties(savedCart, dtoCart);
                dtoCart.setUsername(savedCart.getCustomer().getUsername());
                return dtoCart;

            }
        } else if (cart.getProductType().equals("DRINK")){

            Optional<Drinks> drinksOptional = drinksRepository.findByDrinkId(cart.getProductId());

            if(drinksOptional.isPresent()){

                cart.setPrice(drinksOptional.get().getPrice());
                cart.setProductName(drinksOptional.get().getDrinkName());
                cart.setImageUrl(drinksOptional.get().getImageUrl());

                Optional<Customer> customer = customerRepository.findByUsername(dtoCartUI.getUsername());
                cart.setCustomer(customer.get());
                Cart savedCart = cartRepository.save(cart);

                DtoCart dtoCart = new DtoCart();
                BeanUtils.copyProperties(savedCart, dtoCart);
                dtoCart.setUsername(savedCart.getCustomer().getUsername());
                return dtoCart;

            }
        } else if (cart.getProductType().equals("DESSERT")) {

            Optional<Desserts> dessertsOptional = dessertsRepository.findByDessertId(cart.getProductId());

            if(dessertsOptional.isPresent()){

                cart.setPrice(dessertsOptional.get().getPrice());
                cart.setProductName(dessertsOptional.get().getDessertName());
                cart.setImageUrl(dessertsOptional.get().getImageUrl());

                Optional<Customer> customer = customerRepository.findByUsername(dtoCartUI.getUsername());
                cart.setCustomer(customer.get());
                Cart savedCart = cartRepository.save(cart);

                DtoCart dtoCart = new DtoCart();
                BeanUtils.copyProperties(savedCart, dtoCart);
                dtoCart.setUsername(savedCart.getCustomer().getUsername());
                return dtoCart;

            }
        } else if (cart.getProductType().equals("SNACK")) {

            Optional<Snacks> snacksOptional = snacksRepository.findBySnackId(cart.getProductId());

            if(snacksOptional.isPresent()){

                cart.setPrice(snacksOptional.get().getPrice());
                cart.setProductName(snacksOptional.get().getSnackName());
                cart.setImageUrl(snacksOptional.get().getImageUrl());

                Optional<Customer> customer = customerRepository.findByUsername(dtoCartUI.getUsername());
                cart.setCustomer(customer.get());
                Cart savedCart = cartRepository.save(cart);

                DtoCart dtoCart = new DtoCart();
                BeanUtils.copyProperties(savedCart, dtoCart);
                dtoCart.setUsername(savedCart.getCustomer().getUsername());
                return dtoCart;

            }

        }else{
            throw new RuntimeException("Invalid product type");
        }

        return null;
    }

}
