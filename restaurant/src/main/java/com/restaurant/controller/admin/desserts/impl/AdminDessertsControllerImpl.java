package com.restaurant.controller.admin.desserts.impl;

import com.restaurant.controller.admin.desserts.AdminDessertsController;
import com.restaurant.dto.DtoAddDesserts;
import com.restaurant.dto.DtoDesserts;
import com.restaurant.service.admin.desserts.AdminDessertsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
@Tag(name = "Admin Desserts Controller", description = "Controller for managing desserts in the restaurant")
public class AdminDessertsControllerImpl implements AdminDessertsController {

    private final AdminDessertsService adminDessertsService;

    public AdminDessertsControllerImpl(AdminDessertsService adminDessertsService) {
        this.adminDessertsService = adminDessertsService;
    }

    @Override
    @PostMapping(path = "/add/desserts")
    public DtoDesserts saveDesserts(@RequestBody DtoAddDesserts dtoAddDesserts) {
        return adminDessertsService.saveDesserts(dtoAddDesserts);
    }

    @Override
    @GetMapping(path = "/get/desserts")
    public List<DtoDesserts> getAllDesserts() {
        return adminDessertsService.getAllDesserts();
    }

}
