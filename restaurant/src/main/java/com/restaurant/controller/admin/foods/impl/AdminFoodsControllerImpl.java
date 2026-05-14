package com.restaurant.controller.admin.foods.impl;

import com.restaurant.controller.admin.foods.AdminFoodsController;
import com.restaurant.dto.DtoAddFoods;
import com.restaurant.dto.DtoFoods;
import com.restaurant.service.admin.foods.AdminFoodsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
@Tag(name = "Admin Foods Controller", description = "Controller for managing foods in the restaurant")
public class AdminFoodsControllerImpl implements AdminFoodsController {

    private final AdminFoodsService adminFoodsService;

    public AdminFoodsControllerImpl(AdminFoodsService adminFoodsService) {
        this.adminFoodsService = adminFoodsService;
    }

    @Override
    @PostMapping(path = "/add/foods")
    public DtoFoods saveFood(@RequestBody DtoAddFoods dtoAddFoods) {
        return adminFoodsService.saveFood(dtoAddFoods);
    }

    @Override
    @GetMapping(path = "/get/foods")
    public List<DtoFoods> getAllFoods() {
        return adminFoodsService.getAllFoods();
    }

}
