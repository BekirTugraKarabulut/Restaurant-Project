package com.restaurant.service.admin.foods.impl;

import com.restaurant.dto.DtoAddFoods;
import com.restaurant.dto.DtoFoods;
import com.restaurant.model.Foods;
import com.restaurant.model.ProductType;
import com.restaurant.repository.FoodsRepository;
import com.restaurant.service.admin.foods.AdminFoodsService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminFoodsServiceImpl implements AdminFoodsService {

    private final FoodsRepository foodsRepository;

    public AdminFoodsServiceImpl(FoodsRepository foodsRepository) {
        this.foodsRepository = foodsRepository;
    }

    @CacheEvict(value = "foodsCache", key = "'allFoods'")
    @Override
    public DtoFoods saveFood(DtoAddFoods dtoAddFoods) {

        Foods foods = new Foods();
        foods.setFoodName(dtoAddFoods.getFoodName());
        foods.setDescription(dtoAddFoods.getDescription());
        foods.setPrice(dtoAddFoods.getPrice());
        foods.setImageUrl(dtoAddFoods.getImageUrl());
        foods.setProductType(ProductType.FOOD);

        Foods dbFoods = foodsRepository.save(foods);
        DtoFoods dtoFoods = new DtoFoods();
        dtoFoods.setFoodId(dbFoods.getFoodId());
        dtoFoods.setFoodName(dbFoods.getFoodName());
        dtoFoods.setDescription(dbFoods.getDescription());
        dtoFoods.setPrice(dbFoods.getPrice());
        dtoFoods.setImageUrl(dbFoods.getImageUrl());
        dtoFoods.setProductType(dbFoods.getProductType());

        return dtoFoods;
    }

    @Cacheable(value = "foodsCache", key = "'allFoods'")
    @Override
    public List<DtoFoods> getAllFoods() {

        List<Foods> foodsList = foodsRepository.findAll();

        return foodsList.stream().map(foods -> {
            DtoFoods dtoFoods = new DtoFoods();
            dtoFoods.setFoodId(foods.getFoodId());
            dtoFoods.setFoodName(foods.getFoodName());
            dtoFoods.setDescription(foods.getDescription());
            dtoFoods.setPrice(foods.getPrice());
            dtoFoods.setImageUrl(foods.getImageUrl());
            dtoFoods.setProductType(foods.getProductType());
            return dtoFoods;
        }).toList();
    }

}
