package com.producersapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Producer;
import com.producersapi.model.SaleProducer;
import com.producersapi.repository.ProducerRepository;
import com.producersapi.repository.SaleProducerRepository;
import com.producersapi.util.EntityService;
import java.util.List;
import java.util.Optional;

@Service
public class SaleProducerService implements EntityService<SaleProducer> {
	
	@Autowired
	private SaleProducerRepository repository;
	
	@Autowired
	private ProducerService producerService;
	
	public void saveBy(SaleProducer entity, Integer id) {
		Producer producer = producerService.getProducerById(id);
		entity.setProducer(producer);
		repository.saveAndFlush(entity);
	}
	
	@Override
	public List<SaleProducer> findAll(){
		return repository.findAll();
	}
	
	@Override
	public Optional<SaleProducer> findById(Integer id){
		return repository.findById(id);
	}
	
	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	
	public List<SaleProducer> getListProducer(Integer id) {
		 Producer producer = producerService.getProducerById(id);
		 
		 return producer.gerSalesProducers();		 
	}

	@Override
	public void save(SaleProducer entity) {
		// TODO Auto-generated method stub
		
	}
	
	
}
