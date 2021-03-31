package com.producersapi.util;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface EntityResource<Entity extends Object> {

	@PostMapping
	public ResponseEntity<Entity> save(@RequestBody Entity entity);

	@GetMapping
	public ResponseEntity<List<Entity>> findAll();

	@GetMapping("/{id}")
	public ResponseEntity<Entity> findById(@PathVariable("id") Integer id);

	@PutMapping("/{id}")
	public ResponseEntity<Entity> updateById(@PathVariable("id") Integer id, @RequestBody Entity entity);

	@DeleteMapping("/{id}")
	public ResponseEntity<Entity> deleteById(@PathVariable("id") Integer id);
}
