package com.producersapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Site;
import com.producersapi.model.Task;
import com.producersapi.repository.SiteRepository;
import com.producersapi.repository.TaskRepository;
import com.producersapi.util.EntityService;

@Service
public class SiteService implements EntityService<Site>{

	@Autowired
	private SiteRepository siterepository;

	@Override
	public void save(Site entity) {
		siterepository.saveAndFlush(entity);
	}

	@Override
	public List<Site> findAll() {
		return siterepository.findAll();
	}

	@Override
	public Optional<Site> findById(Integer id) {
		return siterepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		siterepository.deleteById(id);
	}
	
}
