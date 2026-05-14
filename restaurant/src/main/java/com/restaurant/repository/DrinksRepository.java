package com.restaurant.repository;

import com.restaurant.model.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinksRepository extends JpaRepository<Drinks, Long> {

    Optional<Drinks> findByDrinkId(Long drinkId);

}
