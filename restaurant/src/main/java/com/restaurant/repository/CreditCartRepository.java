package com.restaurant.repository;

import com.restaurant.model.CreditCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCartRepository extends JpaRepository<CreditCart , String> {

}
