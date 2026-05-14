package com.restaurant.controller.admin.drinks.impl;

import com.restaurant.controller.admin.drinks.AdminDrinksController;
import com.restaurant.dto.DtoAddDrinks;
import com.restaurant.dto.DtoDrinks;
import com.restaurant.service.admin.drinks.AdminDrinksService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
@Tag(name = "Admin Drinks Controller", description = "Controller for managing drinks in the restaurant")
public class AdminDrinksControllerImpl implements AdminDrinksController {

    private final AdminDrinksService adminDrinksService;

    public AdminDrinksControllerImpl(AdminDrinksService adminDrinksService) {
        this.adminDrinksService = adminDrinksService;
    }

    @Override
    @PostMapping(path = "/add/drinks")
    public DtoDrinks saveDrinks(@RequestBody DtoAddDrinks dtoAddDrinks) {
        return adminDrinksService.saveDrinks(dtoAddDrinks);
    }

    @Override
    @GetMapping(path = "/get/drinks")
    public List<DtoDrinks> getAllDrinks() {
        return adminDrinksService.getAllDrinks();
    }

}
