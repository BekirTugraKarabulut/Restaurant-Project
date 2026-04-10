package com.restaurant.service.admin.drinks.impl;

import com.restaurant.dto.DtoAddDrinks;
import com.restaurant.dto.DtoDrinks;
import com.restaurant.model.Drinks;
import com.restaurant.repository.DrinksRepository;
import com.restaurant.service.admin.drinks.AdminDrinksService;
import org.springframework.stereotype.Service;

@Service
public class AdminDrinksServiceImpl implements AdminDrinksService {

    private final DrinksRepository drinksRepository;

    public AdminDrinksServiceImpl(DrinksRepository drinksRepository) {
        this.drinksRepository = drinksRepository;
    }

    @Override
    public DtoDrinks saveDrinks(DtoAddDrinks dtoAddDrinks) {

        Drinks drinks = new Drinks();
        drinks.setDrinkName(dtoAddDrinks.getDrinkName());
        drinks.setDescription(dtoAddDrinks.getDescription());
        drinks.setPrice(dtoAddDrinks.getPrice());
        Drinks savedDrinks = drinksRepository.save(drinks);

        DtoDrinks dtoDrinks = new DtoDrinks();
        dtoDrinks.setDrinkId(savedDrinks.getDrinkId());
        dtoDrinks.setDrinkName(savedDrinks.getDrinkName());
        dtoDrinks.setDescription(savedDrinks.getDescription());
        dtoDrinks.setPrice(savedDrinks.getPrice());

        return dtoDrinks;
    }
}
