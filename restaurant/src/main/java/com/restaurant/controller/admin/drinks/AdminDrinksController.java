package com.restaurant.controller.admin.drinks;

import com.restaurant.dto.DtoAddDrinks;
import com.restaurant.dto.DtoDrinks;

public interface AdminDrinksController {

    public DtoDrinks saveDrinks(DtoAddDrinks dtoAddDrinks);

}
