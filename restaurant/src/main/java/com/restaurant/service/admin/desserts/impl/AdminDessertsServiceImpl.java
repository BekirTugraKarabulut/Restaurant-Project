package com.restaurant.service.admin.desserts.impl;

import com.restaurant.dto.DtoAddDesserts;
import com.restaurant.dto.DtoDesserts;
import com.restaurant.model.Desserts;
import com.restaurant.repository.DessertsRepository;
import com.restaurant.service.admin.desserts.AdminDessertsService;
import org.springframework.stereotype.Service;

@Service
public class AdminDessertsServiceImpl implements AdminDessertsService {

    private final DessertsRepository dessertsRepository;

    public AdminDessertsServiceImpl(DessertsRepository dessertsRepository) {
        this.dessertsRepository = dessertsRepository;
    }

    @Override
    public DtoDesserts saveDesserts(DtoAddDesserts dtoAddDesserts) {

        Desserts desserts = new Desserts();
        desserts.setDessertName(dtoAddDesserts.getDessertName());
        desserts.setDescription(dtoAddDesserts.getDescription());
        desserts.setPrice(dtoAddDesserts.getPrice());

        Desserts dbDesserts = dessertsRepository.save(desserts);
        DtoDesserts dtoDesserts = new DtoDesserts();
        dtoDesserts.setDessertId(dbDesserts.getDessertId());
        dtoDesserts.setDessertName(dbDesserts.getDessertName());
        dtoDesserts.setDescription(dbDesserts.getDescription());
        dtoDesserts.setPrice(dbDesserts.getPrice());

        return dtoDesserts;
    }


}
