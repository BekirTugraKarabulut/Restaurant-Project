package com.restaurant.repository;

import com.restaurant.model.Desserts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DessertsRepository extends JpaRepository<Desserts , Long> {

}
