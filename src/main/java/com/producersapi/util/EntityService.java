package com.producersapi.util;

import java.util.List;
import java.util.Optional;

public interface EntityService<Entity extends Object> {
	
	public void save(Entity entity);

	public List<Entity> findAll();

	public Optional<Entity> findById(Integer id);

	public void deleteById(Integer id);
}
