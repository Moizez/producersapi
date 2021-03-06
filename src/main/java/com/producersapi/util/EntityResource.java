package com.producersapi.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface EntityResource<Entity extends Object> {

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Entity entity);

	@GetMapping
	public ResponseEntity<?> findAll();

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id);

	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") Integer id, @RequestBody Entity entity);

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id);
}
