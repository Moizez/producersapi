package com.producersapi.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.model.Site;
import com.producersapi.service.SiteService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sites")
public class SiteResource extends Response<Site> implements EntityResource<Site> {

	@Autowired
	private SiteService service;

	@Override
	public ResponseEntity<Site> save(Site entity) {
		service.save(entity);
		return new ResponseEntity<Site>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Site>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<Site> findById(Integer id) {
		return findById(service, id);
	}

	@Override
	public ResponseEntity<Site> updateById(Integer id, Site entity) {
		Optional<Site> site = service.findById(id);

		if (site.isPresent()) {
			BeanUtils.copyProperties(entity, site.get(), "id");

			service.save(site.get());
			return ResponseEntity.ok(site.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Site> deleteById(Integer id) {
		Optional<Site> site = service.findById(id);

		if (site.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
