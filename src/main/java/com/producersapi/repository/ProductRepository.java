package com.producersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.producersapi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Product findByLabel(String label);

	public Product findByValue(Integer Value);

}
