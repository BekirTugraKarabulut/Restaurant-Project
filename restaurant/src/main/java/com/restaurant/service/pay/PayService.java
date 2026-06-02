package com.restaurant.service.pay;

import com.restaurant.dto.DtoCreditCart;
import com.restaurant.dto.DtoCustomer;
import com.restaurant.dto.DtoPay;
import com.restaurant.model.*;
import com.restaurant.repository.*;
import com.restaurant.service.cart.CartPricingService;
import com.restaurant.service.courier.CourierService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private final CourierService courierService;

    public PayService(PayRepository payRepository, CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder, CreditCartRepository creditCartRepository, CartRepository cartRepository, AddressByCustomerRepository addressByCustomerRepository, CartPricingService cartPricingService, CourierService courierService) {
        this.payRepository = payRepository;
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.creditCartRepository = creditCartRepository;
        this.cartRepository = cartRepository;
        this.addressByCustomerRepository = addressByCustomerRepository;
        this.cartPricingService = cartPricingService;
        this.courierService = courierService;
    }

    @Transactional
    public boolean payByUsername(String username , DtoCreditCart dtoCreditCart) throws InterruptedException {

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

            courierService.courierProcess(customer.get().getUsername());

            cartRepository.deleteByCustomer_Username(customer.get().getUsername());

            return true;
        }else{
            return false;
        }

    }

    public List<DtoPay> getOrdersByUsername(String username){

        Optional<Customer> customer = customerRepository.findByUsername(username);

        List<Pay> pays = payRepository.findByCustomer_Username(username);
        List<DtoPay> dtoPays = pays.stream().map(pay -> {

            DtoPay dtoPay = new DtoPay();
            dtoPay.setPayId(pay.getPayId());

            DtoCustomer dtoCustomer = new DtoCustomer();
            BeanUtils.copyProperties(customer.get(), dtoCustomer);
            dtoPay.setCustomer(dtoCustomer);

            dtoPay.setAddress(pay.getAddress());
            dtoPay.setPrice(pay.getPrice());
            return dtoPay;
        }).toList();

        return dtoPays;
    }

}
