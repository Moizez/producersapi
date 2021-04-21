package com.producersapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Task;
import com.producersapi.repository.TaskRepository;
import com.producersapi.util.EntityService;

@Service
public class TaskService implements EntityService<Task>{

	@Autowired
	private TaskRepository repository;

	@Override
	public void save(Task entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public List<Task> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Task> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public List<Task> findByCurrentDate() {
		return repository.findByCurrentDate();
	}

	public List<Task> findByFutureTasks() {
		return repository.findByFutureTasks();
	}
}
