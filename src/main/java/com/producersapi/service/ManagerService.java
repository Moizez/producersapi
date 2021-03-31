package com.producersapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Manager;
import com.producersapi.repository.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository repository;

	public Manager save(Manager manager) {
		return repository.saveAndFlush(manager);
	}

	public List<Manager> findAll() {
		return repository.findAll();
	}

	public Optional<Manager> findById(Integer id) {
		return repository.findById(id);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public Optional<Manager> login(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	public Manager findByEmail(String email) {
		return repository.findByEmail(email);
	}
}
