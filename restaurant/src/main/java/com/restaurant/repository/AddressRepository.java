package com.restaurant.repository;

import com.restaurant.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address , Long> {

    List<Address> findByCustomer_Username(String username);

}
