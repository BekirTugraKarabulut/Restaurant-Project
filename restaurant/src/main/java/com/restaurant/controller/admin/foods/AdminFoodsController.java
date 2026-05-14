package com.restaurant.controller.admin.foods;

import com.restaurant.dto.DtoAddFoods;
import com.restaurant.dto.DtoFoods;

import java.util.List;

public interface AdminFoodsController {

    public DtoFoods saveFood(DtoAddFoods dtoAddFoods);

    public List<DtoFoods> getAllFoods();

}
