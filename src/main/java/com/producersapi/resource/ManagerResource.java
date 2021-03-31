package com.producersapi.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.model.Manager;
import com.producersapi.service.ManagerService;
import com.producersapi.util.EntityResource;

@RestController
@RequestMapping("api/managers")
public class ManagerResource implements EntityResource<Manager> {

	@Autowired
	private ManagerService service;

	@Override
	public ResponseEntity<Manager> save(Manager entity) {
		service.save(entity);
		return new ResponseEntity<Manager>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Manager>> findAll() {
		List<Manager> manager = service.findAll();
		if (manager.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	public ResponseEntity<Manager> findById(Integer id) {
		Optional<Manager> manager = service.findById(id);

		if (manager.isPresent()) {
			return ResponseEntity.ok(manager.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Manager> updateById(Integer id, Manager entity) {
		Optional<Manager> manager = service.findById(id);

		if (manager.isPresent()) {
			BeanUtils.copyProperties(entity, manager.get(), "id");

			service.save(manager.get());
			return ResponseEntity.ok(manager.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Manager> deleteById(Integer id) {
		Optional<Manager> producer = service.findById(id);

		if (producer.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/login")
	public Optional<Manager> login(@RequestBody Manager manager) {
		return (service.login(manager.getEmail(), manager.getPassword()));
	}

}
