package com.restaurant.service.admin.snacks;

import com.restaurant.dto.DtoAddSnacks;
import com.restaurant.dto.DtoSnacks;

public interface AdminSnacksService {

    public DtoSnacks saveSnack(DtoAddSnacks dtoAddSnacks);

}
