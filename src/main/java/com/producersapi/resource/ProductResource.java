package com.producersapi.resource;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.model.Producer;
import com.producersapi.model.Product;
import com.producersapi.reporty.ProductPdfReport;
import com.producersapi.service.ProductService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@CrossOrigin(origins = "*")
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
	
	@GetMapping("/{value}/producers")
	public ResponseEntity<List<Producer>> getProducers(@PathVariable("value") Integer value) {
		Product product = service.findByValue(value);
		
		if (product != null) {
			return ResponseEntity.ok(product.getProducers());
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/pdf/{showProducers}")
	public ResponseEntity<InputStreamResource> findAllPdf(@PathVariable("showProducers") boolean showProducers) {
		List<Product> prodcuts = service.findAll();
		ByteArrayInputStream bis = ProductPdfReport.ProductsReport(prodcuts, showProducers);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=report.pdf");
		return ResponseEntity
		        .ok()
		        .headers(headers)
		        .contentType(MediaType.APPLICATION_PDF)
		        .body(new InputStreamResource(bis));
	}
}
