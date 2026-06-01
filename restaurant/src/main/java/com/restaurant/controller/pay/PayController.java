package com.restaurant.controller.pay;

import com.restaurant.dto.DtoCreditCart;
import com.restaurant.service.pay.PayService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pay")
public class PayController {

    private final PayService payService;

    public PayController(PayService payService) {
        this.payService = payService;
    }

    @PostMapping(path = "/{username}")
    public boolean payByUsername(@PathVariable(name = "username" , required = true) String username ,@RequestBody DtoCreditCart dtoCreditCart){
        return payService.payByUsername(username, dtoCreditCart);
    }

}
