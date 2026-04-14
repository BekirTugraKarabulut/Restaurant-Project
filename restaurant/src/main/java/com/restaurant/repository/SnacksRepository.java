package com.restaurant.repository;

import com.restaurant.model.Snacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnacksRepository extends JpaRepository<Snacks , Long> {

}
