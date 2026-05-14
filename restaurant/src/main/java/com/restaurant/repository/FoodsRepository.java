package com.restaurant.repository;

import com.restaurant.model.Foods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodsRepository extends JpaRepository<Foods, Long> {

    Optional<Foods> findByFoodId(Long foodId);

}
