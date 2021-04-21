package com.producersapi.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.model.Address;
import com.producersapi.service.AddressService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@RequestMapping("/api/adresses")
public class AddressResource extends Response<Address> implements EntityResource<Address> {

	@Autowired
	private AddressService service;

	@Override
	public ResponseEntity<Address> save(Address entity) {
		service.save(entity);
		return new ResponseEntity<Address>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Address>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<Address> findById(Integer id) {
		return findById(service, id);
	}

	@Override
	public ResponseEntity<Address> updateById(Integer id, Address entity) {
		Optional<Address> address = service.findById(id);

		if (address.isPresent()) {
			BeanUtils.copyProperties(entity, address.get(), "id");

			service.save(address.get());
			return ResponseEntity.ok(address.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Address> deleteById(Integer id) {
		Optional<Address> address = service.findById(id);

		if (address.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
