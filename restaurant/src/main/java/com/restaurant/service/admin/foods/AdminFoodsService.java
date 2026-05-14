package com.restaurant.service.admin.foods;

import com.restaurant.dto.DtoAddFoods;
import com.restaurant.dto.DtoFoods;

import java.util.List;

public interface AdminFoodsService {

    public DtoFoods saveFood(DtoAddFoods dtoAddFoods);

    public List<DtoFoods> getAllFoods();
}
