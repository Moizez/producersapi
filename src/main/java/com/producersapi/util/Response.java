package com.producersapi.util;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

public abstract class Response<Entity extends Object> {
	public ResponseEntity<List<Entity>> findAll(List<Entity> entities){
		if (!entities.isEmpty()) {
			return ResponseEntity.ok(entities);
		}
		
		return toNotFoundList();
	}
	
	public ResponseEntity<List<Entity>> findAll(EntityService<Entity> entityService){
		List<Entity> entities = entityService.findAll();
		
		return findAll(entities);
	}
	
	public ResponseEntity<Entity> findById(EntityService<Entity> entityService, Integer id) {
		Optional<Entity> optional = entityService.findById(id);

		return get(optional);
	}

	public ResponseEntity<Entity> get(Optional<Entity> optional) {
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		return getNotFound();
	}
	
	private ResponseEntity<Entity> getNotFound() {
		return ResponseEntity.notFound().build();
	}

	private ResponseEntity<List<Entity>> toNotFoundList() {
		return ResponseEntity.notFound().build();
	}
}
