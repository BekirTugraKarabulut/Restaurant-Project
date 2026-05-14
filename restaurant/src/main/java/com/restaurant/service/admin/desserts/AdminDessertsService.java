package com.restaurant.service.admin.desserts;

import com.restaurant.dto.DtoAddDesserts;
import com.restaurant.dto.DtoDesserts;

import java.util.List;

public interface AdminDessertsService {

    public DtoDesserts saveDesserts(DtoAddDesserts dtoAddDesserts);

    public List<DtoDesserts> getAllDesserts();

}
