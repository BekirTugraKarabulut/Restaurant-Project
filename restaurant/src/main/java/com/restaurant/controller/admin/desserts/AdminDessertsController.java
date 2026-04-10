package com.restaurant.controller.admin.desserts;

import com.restaurant.dto.DtoAddDesserts;
import com.restaurant.dto.DtoDesserts;

public interface AdminDessertsController {

    public DtoDesserts saveDesserts(DtoAddDesserts dtoAddDesserts);

}
