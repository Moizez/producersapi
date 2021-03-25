package com.producersapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Address;
import com.producersapi.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;

	public Address save(Address address) {
		return repository.saveAndFlush(address);
	}

	public List<Address> findAll() {
		return repository.findAll();
	}

	public Address getOne(Integer id) {
		return repository.getOne(id);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

}
