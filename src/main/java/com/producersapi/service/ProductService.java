package com.producersapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Product;
import com.producersapi.repository.ProductRepository;
import com.producersapi.util.EntityService;

@Service
public class ProductService implements EntityService<Product> {

	@Autowired
	private ProductRepository repository;

	@Override
	public void save(Product entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public List<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public Product findByLabel(String label) {
		return repository.findByLabel(label);
	}

	public Product findByValue(Integer value) {
		return repository.findByValue(value);
	}

}