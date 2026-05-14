package com.restaurant.controller.admin.snacks.impl;

import com.restaurant.controller.admin.snacks.AdminSnacksController;
import com.restaurant.dto.DtoAddSnacks;
import com.restaurant.dto.DtoSnacks;
import com.restaurant.service.admin.snacks.AdminSnacksService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminSnacksControllerImpl implements AdminSnacksController {

    private final AdminSnacksService adminSnacksService;

    public AdminSnacksControllerImpl(AdminSnacksService adminSnacksService) {
        this.adminSnacksService = adminSnacksService;
    }

    @Override
    @PostMapping(path = "/add/snack")
    public DtoSnacks saveSnack(@RequestBody DtoAddSnacks dtoAddSnacks) {
        return adminSnacksService.saveSnack(dtoAddSnacks);
    }

    @Override
    @GetMapping(path = "/get/snacks")
    public List<DtoSnacks> getAllSnacks() {
        return adminSnacksService.getAllSnacks();
    }

}
