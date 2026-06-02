package com.restaurant.controller.courier;

import com.restaurant.dto.DtoCourier;
import com.restaurant.service.courier.CourierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/courier")
public class CourierController {

    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping(path = "/process/{username}")
    public String courierProcess(@PathVariable(name = "username" , required = true) String username) throws InterruptedException {
        return courierService.courierProcess(username);
    }

    @GetMapping(path = "/{username}")
    public List<DtoCourier> getCourierByUsername(@PathVariable(name = "username" , required = true) String username){
        return courierService.getCourierByUsername(username);
    }



}
