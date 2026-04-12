package com.restaurant.service.favorites;

import com.restaurant.model.Favorites;
import com.restaurant.repository.FavoritesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;

    public FavoritesService(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    public List<Favorites> getAllFavorites() {
        return favoritesRepository.findAll();
    }

}
