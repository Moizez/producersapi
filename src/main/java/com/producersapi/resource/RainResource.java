package com.producersapi.resource;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	private RainService service;

	@Override
	public ResponseEntity<Rain> save(Rain entity) {
		service.save(entity);
		return new ResponseEntity<Rain>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Rain>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<Rain> findById(Integer id) {
		return findById(service, id);
	}

	@Override
	public ResponseEntity<Rain> updateById(Integer id, Rain entity) {
		Optional<Rain> rain = service.findById(id);

		if (rain.isPresent()) {
			BeanUtils.copyProperties(entity, rain.get(), "id");

			service.save(rain.get());
			return ResponseEntity.ok(rain.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Rain> deleteById(Integer id) {
		Optional<Rain> rain = service.findById(id);

		if (rain.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
