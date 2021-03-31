package com.producersapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.FarmingActivity;
import com.producersapi.repository.FarmingActivityRepository;
import com.producersapi.util.EntityService;

@Service
public class FarmingActivityService implements EntityService<FarmingActivity> {

	@Autowired
	private FarmingActivityRepository repository;

	@Override
	public void save(FarmingActivity entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public List<FarmingActivity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<FarmingActivity> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
}
