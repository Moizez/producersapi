package com.producersapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Producer;
import com.producersapi.repository.ProducerRepository;

@Service
public class ProducerService {

	@Autowired
	private ProducerRepository repository;

	public Producer save(Producer producer) {
		return repository.saveAndFlush(producer);
	}

	public List<Producer> findAll() {
		return repository.findAll();
	}

	public Optional<Producer> getOne(Integer id) {
		return repository.findById(id);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

}
