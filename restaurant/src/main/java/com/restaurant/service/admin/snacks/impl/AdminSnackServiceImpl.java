package com.restaurant.service.admin.snacks.impl;

import com.restaurant.dto.DtoAddSnacks;
import com.restaurant.dto.DtoSnacks;
import com.restaurant.model.Snacks;
import com.restaurant.repository.SnacksRepository;
import com.restaurant.service.admin.snacks.AdminSnacksService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSnackServiceImpl implements AdminSnacksService {

    private final SnacksRepository snacksRepository;

    public AdminSnackServiceImpl(SnacksRepository snacksRepository) {
        this.snacksRepository = snacksRepository;
    }


    @Override
    public DtoSnacks saveSnack(DtoAddSnacks dtoAddSnacks) {

        Snacks snack = new Snacks();
        snack.setSnackName(dtoAddSnacks.getSnackName());
        snack.setDescription(dtoAddSnacks.getDescription());
        snack.setPrice(dtoAddSnacks.getPrice());

        Snacks dbSnack = snacksRepository.save(snack);
        DtoSnacks dtoSnacks = new DtoSnacks();
        dtoSnacks.setSnackId(dbSnack.getSnackId());
        dtoSnacks.setSnackName(dbSnack.getSnackName());
        dtoSnacks.setDescription(dbSnack.getDescription());
        dtoSnacks.setPrice(dbSnack.getPrice());

        return dtoSnacks;
    }

    @Cacheable(value = "snacksCache", key = "'allSnacks'")
    @Override
    public List<DtoSnacks> getAllSnacks() {

        List<Snacks> snacksList = snacksRepository.findAll();
        List<DtoSnacks> dtoSnacksList = snacksList.stream().map(snack -> {
            DtoSnacks dtoSnacks = new DtoSnacks();
            dtoSnacks.setSnackId(snack.getSnackId());
            dtoSnacks.setSnackName(snack.getSnackName());
            dtoSnacks.setDescription(snack.getDescription());
            dtoSnacks.setPrice(snack.getPrice());
            return dtoSnacks;
        }).toList();

           return dtoSnacksList;

    }
}
