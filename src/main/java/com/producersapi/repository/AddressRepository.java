package com.producersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.producersapi.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
