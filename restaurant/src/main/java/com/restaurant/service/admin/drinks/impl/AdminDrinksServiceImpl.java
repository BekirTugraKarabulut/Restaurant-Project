package com.restaurant.service.admin.drinks.impl;

import com.restaurant.dto.DtoAddDrinks;
import com.restaurant.dto.DtoDrinks;
import com.restaurant.model.Drinks;
import com.restaurant.model.ProductType;
import com.restaurant.repository.DrinksRepository;
import com.restaurant.service.admin.drinks.AdminDrinksService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminDrinksServiceImpl implements AdminDrinksService {

    private final DrinksRepository drinksRepository;

    public AdminDrinksServiceImpl(DrinksRepository drinksRepository) {
        this.drinksRepository = drinksRepository;
    }

    @CacheEvict(value = "drinksCache", key = "'allDrinks'")
    @Override
    public DtoDrinks saveDrinks(DtoAddDrinks dtoAddDrinks) {

        Drinks drinks = new Drinks();
        drinks.setDrinkName(dtoAddDrinks.getDrinkName());
        drinks.setDescription(dtoAddDrinks.getDescription());
        drinks.setPrice(dtoAddDrinks.getPrice());
        drinks.setImageUrl(dtoAddDrinks.getImageUrl());
        drinks.setProductType(ProductType.DRINK);
        Drinks savedDrinks = drinksRepository.save(drinks);

        DtoDrinks dtoDrinks = new DtoDrinks();
        dtoDrinks.setDrinkId(savedDrinks.getDrinkId());
        dtoDrinks.setDrinkName(savedDrinks.getDrinkName());
        dtoDrinks.setDescription(savedDrinks.getDescription());
        dtoDrinks.setPrice(savedDrinks.getPrice());
        dtoDrinks.setImageUrl(savedDrinks.getImageUrl());
        dtoDrinks.setProductType(savedDrinks.getProductType());

        return dtoDrinks;
    }

    @Cacheable(value = "drinksCache", key = "'allDrinks'")
    @Override
    public List<DtoDrinks> getAllDrinks() {

        List<Drinks> drinksList = drinksRepository.findAll();
        List<DtoDrinks> dtoDrinksList = new ArrayList<>();

        if(drinksList.isEmpty()){
            throw new RuntimeException("No Drinks found");
        }else{
            for (Drinks drinks : drinksList) {
                DtoDrinks dtoDrinks = new DtoDrinks();
                dtoDrinks.setDrinkId(drinks.getDrinkId());
                dtoDrinks.setDrinkName(drinks.getDrinkName());
                dtoDrinks.setDescription(drinks.getDescription());
                dtoDrinks.setPrice(drinks.getPrice());
                dtoDrinks.setImageUrl(drinks.getImageUrl());
                dtoDrinks.setProductType(drinks.getProductType());
                dtoDrinksList.add(dtoDrinks);

            }
            return dtoDrinksList;
        }

    }
}
