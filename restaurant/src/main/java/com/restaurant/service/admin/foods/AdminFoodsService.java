package com.restaurant.service.admin.foods;

import com.restaurant.dto.DtoAddFoods;
import com.restaurant.dto.DtoFoods;

public interface AdminFoodsService {

    public DtoFoods saveFood(DtoAddFoods dtoAddFoods);

}
