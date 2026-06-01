package com.restaurant.service.pay;

import com.restaurant.dto.DtoCreditCart;
import com.restaurant.model.*;
import com.restaurant.repository.*;
import com.restaurant.service.cart.CartPricingService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PayService {

    private final PayRepository payRepository;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CreditCartRepository creditCartRepository;
    private final CartRepository cartRepository;
    private final AddressByCustomerRepository addressByCustomerRepository;
    private final CartPricingService cartPricingService;

    public PayService(PayRepository payRepository, CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder, CreditCartRepository creditCartRepository, CartRepository cartRepository, AddressByCustomerRepository addressByCustomerRepository, CartPricingService cartPricingService) {
        this.payRepository = payRepository;
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.creditCartRepository = creditCartRepository;
        this.cartRepository = cartRepository;
        this.addressByCustomerRepository = addressByCustomerRepository;
        this.cartPricingService = cartPricingService;
    }

    @Transactional
    public boolean payByUsername(String username , DtoCreditCart dtoCreditCart) {

        Optional<Customer> customer = customerRepository.findByUsername(username);

        if(customer.isPresent()){

            CreditCart cart = new CreditCart();
            cart.setCardNumber(dtoCreditCart.getCardNumber());
            cart.setMmyy(dtoCreditCart.getMmyy());
            cart.setCvv(bCryptPasswordEncoder.encode(dtoCreditCart.getCvv()));
            creditCartRepository.save(cart);

            Pay pay = new Pay();
            pay.setCustomer(customer.get());

            Optional<Address> address = addressByCustomerRepository.findByCustomer_Username(customer.get().getUsername());
            pay.setAddress(address.get().getStreet());

            Integer payResult = cartPricingService.calculateTotalPrice(customer.get().getUsername());
            pay.setPrice(payResult);
            payRepository.save(pay);

            cartRepository.deleteByCustomer_Username(customer.get().getUsername());

            return true;
        }else{
            return false;
        }

    }

}
