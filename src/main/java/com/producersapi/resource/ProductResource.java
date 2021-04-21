package com.producersapi.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.model.Product;
import com.producersapi.service.ProductService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@RequestMapping("/api/products")
public class ProductResource extends Response<Product> implements EntityResource<Product> {

	@Autowired
	private ProductService service;

	@Override
	public ResponseEntity<Product> save(Product entity) {
		service.save(entity);
		return new ResponseEntity<Product>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Product>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<Product> findById(Integer id) {
		return findById(service, id);
	}

	@Override
	public ResponseEntity<Product> updateById(Integer id, Product entity) {
		Optional<Product> product = service.findById(id);

		if (product.isPresent()) {
			BeanUtils.copyProperties(entity, product.get(), "id");

			service.save(product.get());
			return ResponseEntity.ok(product.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Product> deleteById(Integer id) {
		Optional<Product> product = service.findById(id);

		if (product.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
