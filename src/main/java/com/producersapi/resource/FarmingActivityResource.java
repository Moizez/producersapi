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

import com.producersapi.model.FarmingActivity;
import com.producersapi.service.FarmingActivityService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/farming-activities")
public class FarmingActivityResource extends Response<FarmingActivity> implements EntityResource<FarmingActivity> {

	@Autowired
	private FarmingActivityService service;

	@Override
	public ResponseEntity<FarmingActivity> save(FarmingActivity entity) {
		service.save(entity);
		return new ResponseEntity<FarmingActivity>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<FarmingActivity>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<FarmingActivity> findById(Integer id) {
		return findById(service, id);
	}

	@Override
	public ResponseEntity<FarmingActivity> updateById(Integer id, FarmingActivity entity) {
		Optional<FarmingActivity> farmingActivity = service.findById(id);

		if (farmingActivity.isPresent()) {
			BeanUtils.copyProperties(entity, farmingActivity.get(), "id");

			service.save(farmingActivity.get());
			return ResponseEntity.ok(farmingActivity.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<FarmingActivity> deleteById(Integer id) {
		Optional<FarmingActivity> farmingActivity = service.findById(id);

		if (farmingActivity.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
