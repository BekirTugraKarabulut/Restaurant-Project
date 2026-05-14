package com.restaurant.controller.admin.desserts;

import com.restaurant.dto.DtoAddDesserts;
import com.restaurant.dto.DtoDesserts;

import java.util.List;

public interface AdminDessertsController {

    public DtoDesserts saveDesserts(DtoAddDesserts dtoAddDesserts);

    public List<DtoDesserts> getAllDesserts();

}
