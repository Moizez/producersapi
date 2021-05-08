package com.producersapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.ActivityName;
import com.producersapi.repository.ActivityNameRepository;
import com.producersapi.util.EntityService;

@Service
public class ActivityNameService implements EntityService<ActivityName> {

	@Autowired
	private ActivityNameRepository repository;

	@Override
	public void save(ActivityName entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public List<ActivityName> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<ActivityName> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public ActivityName findByLabel(String label) {
		return repository.findByLabel(label);
	}

	public ActivityName findByValue(Integer value) {
		return repository.findByValue(value);
	}

}