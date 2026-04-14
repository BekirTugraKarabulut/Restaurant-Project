package com.restaurant.controller.admin.snacks;

import com.restaurant.dto.DtoAddSnacks;
import com.restaurant.dto.DtoSnacks;

public interface AdminSnacksController {

    public DtoSnacks saveSnack(DtoAddSnacks dtoAddSnacks);

}
