package com.restaurant.repository;

import com.restaurant.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<Pay , Long> {


}
