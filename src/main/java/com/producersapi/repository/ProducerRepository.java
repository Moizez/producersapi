package com.producersapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.producersapi.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Integer>{

	public Optional<Producer> findByEmailAndPassword(String email, String password);
	

}
