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

import com.producersapi.model.Producer;
import com.producersapi.service.ProducerService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@RequestMapping("/api/producers")
public class ProducerResource extends Response<Producer> implements EntityResource<Producer> {

	@Autowired
	private ProducerService service;

	@Override
	public ResponseEntity<Producer> save(Producer entity) {
		service.save(entity);
		return new ResponseEntity<Producer>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Producer>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<Producer> findById(Integer id) {
		return findById(service, id);
	}

	@Override
	public ResponseEntity<Producer> updateById(Integer id, Producer entity) {
		Optional<Producer> producer = service.findById(id);

		if (producer.isPresent()) {
			BeanUtils.copyProperties(entity, producer.get(), "id");

			service.save(producer.get());
			return ResponseEntity.ok(producer.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Producer> deleteById(Integer id) {
		Optional<Producer> producer = service.findById(id);

		if (producer.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Producer producer) {
		return get(service.login(producer.getEmail(), producer.getPassword()));
	}

}
