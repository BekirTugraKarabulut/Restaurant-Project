package com.restaurant.controller.favorites;

import com.restaurant.model.Favorites;
import com.restaurant.service.favorites.FavoritesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FavoritesController {

    private final FavoritesService favoritesService;

    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
     }

    @GetMapping(path = "/favorites")
    public List<Favorites> getAllFavorites() {
        return favoritesService.getAllFavorites();
    }

}
