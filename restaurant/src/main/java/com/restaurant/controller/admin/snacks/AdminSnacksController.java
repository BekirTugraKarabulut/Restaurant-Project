package com.restaurant.controller.admin.snacks;

import com.restaurant.dto.DtoAddSnacks;
import com.restaurant.dto.DtoSnacks;

import java.util.List;

public interface AdminSnacksController {

    public DtoSnacks saveSnack(DtoAddSnacks dtoAddSnacks);

    public List<DtoSnacks> getAllSnacks();

}
