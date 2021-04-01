package com.producersapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Manager;
import com.producersapi.repository.ManagerRepository;
import com.producersapi.util.EntityService;

@Service
public class ManagerService implements EntityService<Manager> {

	@Autowired
	private ManagerRepository repository;

	@Override
	public void save(Manager entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public List<Manager> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Manager> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public Optional<Manager> onSignIn(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	public Manager findByEmail(String email) {
		return repository.findByEmail(email);
	}

}
