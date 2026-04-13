package com.restaurant.repository;

import com.restaurant.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressByCustomerRepository extends JpaRepository<Address , Long> {

    Optional<Address> findByCustomer_Username(String username);

}
