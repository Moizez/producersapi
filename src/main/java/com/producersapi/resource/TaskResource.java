package com.producersapi.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.model.Task;
import com.producersapi.service.TaskService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tasks")
public class TaskResource extends Response<Task> implements EntityResource<Task> {

	@Autowired
	private TaskService service;

	@Override
	public ResponseEntity<Task> save(Task entity) {
		service.save(entity);
		return new ResponseEntity<Task>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Task>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<Task> findById(Integer id) {
		return findById(service, id);
	}

	@Override
	public ResponseEntity<Task> updateById(Integer id, Task entity) {
		Optional<Task> task = service.findById(id);

		System.out.println(entity.isStatus());
		
		if (entity.isStatus() == false)
			entity.setStatus(true);
		else
			entity.setStatus(false);

		if (task.isPresent()) {
			BeanUtils.copyProperties(entity, task.get(), "id", "description", "date");

			service.save(task.get());
			return ResponseEntity.ok(task.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Task> deleteById(Integer id) {
		Optional<Task> task = service.findById(id);

		if (task.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/todaytasks")
	public List<Task> findByCurrentDate() {
		return service.findByCurrentDate();
	}

	@GetMapping("/futuretasks")
	public List<Task> findByFutureTasks() {
		return service.findByFutureTasks();
	}
}
