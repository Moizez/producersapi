package com.producersapi.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.model.Rain;
import com.producersapi.model.Site;
import com.producersapi.service.RainService;
import com.producersapi.service.SiteService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rains")
public class RainResource extends Response<Rain> implements EntityResource<Rain> {

	@Autowired
	private RainService rainService;
	
	@Autowired
	private SiteService siteService;

	@Override
	public ResponseEntity<Rain> save(Rain entity) {
		rainService.save(entity);
		return new ResponseEntity<Rain>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Rain>> findAll() {
		return findAll(rainService);
	}

	@Override
	public ResponseEntity<Rain> findById(Integer id) {
		return findById(rainService, id);
	}

	@Override
	public ResponseEntity<Rain> updateById(Integer id, Rain entity) {
		Optional<Rain> rain = rainService.findById(id);

		if (rain.isPresent()) {
			BeanUtils.copyProperties(entity, rain.get(), "id");

			rainService.save(rain.get());
			return ResponseEntity.ok(rain.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Rain> deleteById(Integer id) {
		Optional<Rain> rain = rainService.findById(id);

		if (rain.isPresent()) {
			rainService.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/ByPeriod/{init}/{end}")
	public ResponseEntity<List<Site>> findBySiteAndPeriod(
			@PathVariable("init") String init, 
			@PathVariable("end") String end
			){
		List<Site> sites = siteService.findAll();
		List<Site> newSites = new ArrayList<>();
		for(Site site : sites) {
			site.setRains(rainService.FindBySiteAndPeriod(site.getId(), init, end+"z"));
			newSites.add(site);
		}
		return ResponseEntity.ok(newSites);
	}
	
	@GetMapping("/BySite/{id}/{init}/{end}")
	public ResponseEntity<Site> findBySite(
			@PathVariable("id") Integer siteId, 
			@PathVariable("init") String init, 
			@PathVariable("end") String end
			){
		System.out.println(siteId+" - "+init+" - "+end);
		Optional<Site> s = siteService.findById(siteId);
		if(s.isPresent()) {
			Site site = s.get();
			site.setRains(rainService.FindBySiteAndPeriod(siteId, init, end+"z"));
			return ResponseEntity.ok(site);
		}
		return ResponseEntity.notFound().build();
	}

}