package com.producersapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.FarmingActivity;
import com.producersapi.repository.FarmingActivityRepository;

@Service
public class FarmingActivityService {
	
	@Autowired
	private FarmingActivityRepository repository;

	public FarmingActivity save(FarmingActivity farmingActivity) {
		return repository.saveAndFlush(farmingActivity);
	}

	public List<FarmingActivity> findAll() {
		return repository.findAll();
	}

	public Optional<FarmingActivity> findById(Integer id) {
		return repository.findById(id);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

}
