package com.restaurant.repository;

import com.restaurant.model.Desserts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DessertsRepository extends JpaRepository<Desserts , Long> {

    Optional<Desserts> findByDessertId(Long dessertId);

}
