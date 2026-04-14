package com.restaurant.controller.admin.snacks.impl;

import com.restaurant.controller.admin.snacks.AdminSnacksController;
import com.restaurant.dto.DtoAddSnacks;
import com.restaurant.dto.DtoSnacks;
import com.restaurant.service.admin.snacks.AdminSnacksService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
