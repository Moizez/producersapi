package com.producersapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Producer;
import com.producersapi.repository.ProducerRepository;
import com.producersapi.util.EntityService;

@Service
public class ProducerService implements EntityService<Producer> {

	@Autowired
	private ProducerRepository repository;

	@Override
	public void save(Producer entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public List<Producer> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Producer> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public Optional<Producer> onSignIn(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	public List<Producer> findByActiveProducers() {
		return repository.findByActiveProducers();
	}
		
	public Producer findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public List<Producer> findByNameOrNickname(String name) {
		return repository.findByNameOrNickname(name);
	}

	public List<Producer> findByManager(Integer id) {
		return repository.findByManager(id);
	}
	
	public List<Producer> findByActivity(Integer activityId) {
		List<Producer> producers = findAll(); 
		List<Producer> producersByActivity = new ArrayList<Producer>();
		producers.forEach(producer -> {
			if(
					producer.getFarmingActivity() != null &&
					activityId == producer.getFarmingActivity().getActivityName().getValue()){
				producersByActivity.add(producer);
			}
		});
		return producersByActivity;
	}

}