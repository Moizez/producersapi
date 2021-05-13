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
import com.producersapi.service.ActivityNameService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/activities")
public class ActivityNameResource extends Response<ActivityName> implements EntityResource<ActivityName> {

	@Autowired
	private ActivityNameService service;

	@Override
	public ResponseEntity<ActivityName> save(ActivityName entity) {
		service.save(entity);
		return new ResponseEntity<ActivityName>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<ActivityName>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<ActivityName> findById(Integer id) {
		return findById(service, id);
	}

	@Override
	public ResponseEntity<ActivityName> updateById(Integer id, ActivityName entity) {
		Optional<ActivityName> activity = service.findById(id);

		if (activity.isPresent()) {
			BeanUtils.copyProperties(entity, activity.get(), "id");

			service.save(activity.get());
			return ResponseEntity.ok(activity.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<ActivityName> deleteById(Integer id) {
		Optional<ActivityName> activity = service.findById(id);

		if (activity.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
}
