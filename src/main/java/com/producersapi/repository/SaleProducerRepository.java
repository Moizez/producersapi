package com.producersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.producersapi.model.SaleProducer;

@Repository
public interface SaleProducerRepository extends JpaRepository<SaleProducer, Integer> {
	
	
}