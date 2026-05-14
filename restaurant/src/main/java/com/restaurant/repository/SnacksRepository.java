package com.restaurant.repository;

import com.restaurant.model.Snacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SnacksRepository extends JpaRepository<Snacks , Long> {

    Optional<Snacks> findBySnackId(Long snackId);

}
