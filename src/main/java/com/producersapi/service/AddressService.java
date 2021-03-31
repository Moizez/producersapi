package com.producersapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Address;
import com.producersapi.repository.AddressRepository;
import com.producersapi.util.EntityService;

@Service
public class AddressService implements EntityService<Address>{

	@Autowired
	private AddressRepository repository;

	@Override
	public void save(Address entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public List<Address> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Address> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
}
