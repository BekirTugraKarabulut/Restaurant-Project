package com.restaurant.service.admin.foods.impl;

import com.restaurant.dto.DtoAddFoods;
import com.restaurant.dto.DtoFoods;
import com.restaurant.model.Foods;
import com.restaurant.repository.FoodsRepository;
import com.restaurant.service.admin.foods.AdminFoodsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminFoodsServiceImpl implements AdminFoodsService {

    private final FoodsRepository foodsRepository;

    public AdminFoodsServiceImpl(FoodsRepository foodsRepository) {
        this.foodsRepository = foodsRepository;
    }

    @Override
    public DtoFoods saveFood(DtoAddFoods dtoAddFoods) {

        Foods foods = new Foods();
        foods.setFoodName(dtoAddFoods.getFoodName());
        foods.setDescription(dtoAddFoods.getDescription());
        foods.setPrice(dtoAddFoods.getPrice());
        foods.setImageUrl(dtoAddFoods.getImageUrl());

        Foods dbFoods = foodsRepository.save(foods);
        DtoFoods dtoFoods = new DtoFoods();
        dtoFoods.setFoodId(dbFoods.getFoodId());
        dtoFoods.setFoodName(dbFoods.getFoodName());
        dtoFoods.setDescription(dbFoods.getDescription());
        dtoFoods.setPrice(dbFoods.getPrice());
        dtoFoods.setImageUrl(dbFoods.getImageUrl());

        return dtoFoods;
    }

    @Cacheable(value = "foodsCache", key = "'allFoods'")
    @Override
    public List<DtoFoods> getAllFoods() {

        List<Foods> foodsList = foodsRepository.findAll();
        List<DtoFoods> dtoFoodsList = foodsList.stream().map(foods -> {
            DtoFoods dtoFoods = new DtoFoods();
            dtoFoods.setFoodId(foods.getFoodId());
            dtoFoods.setFoodName(foods.getFoodName());
            dtoFoods.setDescription(foods.getDescription());
            dtoFoods.setPrice(foods.getPrice());
            dtoFoods.setImageUrl(foods.getImageUrl());
            return dtoFoods;
        }).toList();

        return dtoFoodsList;
    }

}
