package com.restaurant.service.admin.snacks;

import com.restaurant.dto.DtoAddSnacks;
import com.restaurant.dto.DtoSnacks;

import java.util.List;

public interface AdminSnacksService {

    public DtoSnacks saveSnack(DtoAddSnacks dtoAddSnacks);

    public List<DtoSnacks> getAllSnacks();

}
