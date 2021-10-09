package com.producersapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Rain;
import com.producersapi.model.Site;
import com.producersapi.model.Task;
import com.producersapi.repository.RainRepository;
import com.producersapi.repository.SiteRepository;
import com.producersapi.repository.TaskRepository;
import com.producersapi.util.EntityService;

@Service
public class RainService implements EntityService<Rain>{

	@Autowired
	private RainRepository rainrepository;

	@Override
	public void save(Rain entity) {
		rainrepository.saveAndFlush(entity);
	}

	@Override
	public List<Rain> findAll() {
		return rainrepository.findAll();
	}

	@Override
	public Optional<Rain> findById(Integer id) {
		return rainrepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		rainrepository.deleteById(id);
	}

	public List<Rain> FindBySite(Site site) {
		return rainrepository.findBySite(site);
	}
	
	public List<Rain> FindBySites(List<Site> sites) {
		return rainrepository.findBySites(sites);
	}
	
	public List<Rain> FindByDate(Date date) {
		return rainrepository.findByDate(date);
	}
	
	public List<Rain> FindByPeriod(Date init,  Date end) {
		return rainrepository.findByPeriod(init, end);
	}
	
}
