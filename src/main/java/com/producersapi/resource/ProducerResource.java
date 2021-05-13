package com.producersapi.resource;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
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
import com.producersapi.reporty.ProducerPdfReport;
import com.producersapi.reporty.ProductPdfReport;
import com.producersapi.service.ProducerService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/producers")
public class ProducerResource extends Response<Producer> implements EntityResource<Producer> {

	@Autowired
	private ProducerService service;

	@Override
	public ResponseEntity<Producer> save(Producer entity) {

		service.save(entity);
		return new ResponseEntity<Producer>(entity, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Producer>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<Producer> findById(Integer id) {
		return findById(service, id);
	}

	@Override
	public ResponseEntity<Producer> updateById(Integer id, Producer entity) {
		Optional<Producer> producer = service.findById(id);

		if (producer.isPresent()) {

			BeanUtils.copyProperties(entity, producer.get(), "id", "password");

			service.save(producer.get());
			return ResponseEntity.ok(producer.get());
		}

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Producer> deleteById(Integer id) {
		Optional<Producer> producer = service.findById(id);

		if (producer.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/active")
	public List<Producer> findByActiveProducers() {
		return service.findByActiveProducers();
	}

	@GetMapping("/findByName0rNickname/{name}")
	public List<Producer> findByNameOrNickname(@PathVariable("name") String name) {
		return service.findByNameOrNickname(name);
	}

	@GetMapping("/findProducersByManager")
	public List<Producer> findByManager(@PathVariable("id") Integer id) {
		return service.findByManager(id);
	}

	@GetMapping("/findByActivity/{activityId}")
	public ResponseEntity<List<Producer>> findByActivity(@PathVariable("activityId") Integer activityId) {
		return ResponseEntity.ok(service.findByActivity(activityId));
	}
	
	@GetMapping("/pdf/{showProducts}")
	public ResponseEntity<InputStreamResource> findAllPdf(@PathVariable("showProducts") boolean showProducts) {
		List<Producer> prodcuers = service.findAll();
		ByteArrayInputStream bis = ProducerPdfReport.ProducersReport(prodcuers, showProducts);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=reportProducers.pdf");
		return ResponseEntity
		        .ok()
		        .headers(headers)
		        .contentType(MediaType.APPLICATION_PDF)
		        .body(new InputStreamResource(bis));
	}
	
	@GetMapping("/pdf")
	public ResponseEntity<InputStreamResource> findAllPdf() {
		return findAllPdf(false); 
	}
	
	@GetMapping("/{id}/pdf/{showProducts}")
	public ResponseEntity<InputStreamResource> findOnePdf(@PathVariable("id") Integer id, @PathVariable("showProducts") boolean showProducts) {
		List<Producer> prodcuers = new ArrayList<>();
		prodcuers.add(service.findById(id).get());
		ByteArrayInputStream bis = ProducerPdfReport.ProducersReport(prodcuers, showProducts);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=reportProducers.pdf");
		return ResponseEntity
		        .ok()
		        .headers(headers)
		        .contentType(MediaType.APPLICATION_PDF)
		        .body(new InputStreamResource(bis));
	}
	
	@GetMapping("/{id}/pdf")
	public ResponseEntity<InputStreamResource> findByIdPdf(@PathVariable("id") Integer id) {
		return findOnePdf(id, false);
	}
}