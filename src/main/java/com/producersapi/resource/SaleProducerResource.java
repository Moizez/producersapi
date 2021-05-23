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

import com.producersapi.model.ActivityName;
import com.producersapi.model.SaleProducer;
import com.producersapi.service.SaleProducerService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sales-producers")
public class SaleProducerResource extends Response<SaleProducer> implements EntityResource<SaleProducer> {

	@Autowired
	private SaleProducerService service;
	
	@Override
	public ResponseEntity<SaleProducer> save(SaleProducer entity) {
		service.save(entity);
		return new ResponseEntity<SaleProducer>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<SaleProducer>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<SaleProducer> findById(Integer id) {
		return findById(service, id);
	}
	
	@Override
	public ResponseEntity<SaleProducer> updateById(Integer id, SaleProducer entity) {
		Optional<SaleProducer> activity = service.findById(id);

		if (activity.isPresent()) {
			BeanUtils.copyProperties(entity, activity.get(), "id");

			service.save(activity.get());
			return ResponseEntity.ok(activity.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@Override
	public ResponseEntity<SaleProducer> deleteById(Integer id) {
		Optional<SaleProducer> activity = service.findById(id);

		if (activity.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}