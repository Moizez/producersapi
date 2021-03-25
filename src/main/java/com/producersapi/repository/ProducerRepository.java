package com.producersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.producersapi.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Integer>{

}
