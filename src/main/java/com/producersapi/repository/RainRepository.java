package com.producersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.producersapi.model.Rain;

@Repository
public interface RainRepository extends JpaRepository<Rain, Integer> {

}
