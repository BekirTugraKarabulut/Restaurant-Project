package com.restaurant.controller.admin.drinks;

import com.restaurant.dto.DtoAddDrinks;
import com.restaurant.dto.DtoDrinks;

import java.util.List;

public interface AdminDrinksController {

    public DtoDrinks saveDrinks(DtoAddDrinks dtoAddDrinks);

    public List<DtoDrinks> getAllDrinks();

}
