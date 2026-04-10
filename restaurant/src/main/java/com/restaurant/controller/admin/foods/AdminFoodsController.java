package com.restaurant.controller.admin.foods;

import com.restaurant.dto.DtoAddFoods;
import com.restaurant.dto.DtoFoods;

public interface AdminFoodsController {

    public DtoFoods saveFood(DtoAddFoods dtoAddFoods);

}
