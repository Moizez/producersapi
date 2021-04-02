package com.producersapi.jobs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.producersapi.enums.ActivitiesName;
import com.producersapi.enums.Period;
import com.producersapi.enums.ProductsName;
import com.producersapi.model.Address;
import com.producersapi.model.FarmingActivity;
import com.producersapi.model.Manager;
import com.producersapi.model.Producer;
import com.producersapi.service.ManagerService;
import com.producersapi.service.ProducerService;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ProducerService producerService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createManager();
		createProducer();
		System.out.println("----- Usuários Criados com Sucesso! -----");
	}

	private void createManager() {
		Manager manager = managerService.findByEmail("leo@gmail.com");
		
		Address address = new Address();
		address.setUf("RN");
		address.setCity("Itaú");
		address.setZipCode("59855-000");
		address.setDistrict("Centro");
		address.setStreet("Rua Cicero Celino");
		address.setHouseNumber("44");
		address.setReference("Próximo ao espetinho do Léo");

		if (manager == null) {
			manager = new Manager();
			manager.setName("Leandro Rêgo");
			manager.setNickname("Léo");
			manager.setCpf("056.234.345-78");
			manager.setBirthDate(new Date());
			manager.setPhone("(84)95555-5555");
			manager.setEmail("leo@gmail.com");
			manager.setPassword("123");
			manager.setAddress(address);
			managerService.save(manager);
		}
	}

	private void createProducer() {
		Producer producer = producerService.findByEmail("vina@gmail.com");
		Manager manager = managerService.findByEmail("leo@gmail.com");

		FarmingActivity farmingActivity = new FarmingActivity();
		farmingActivity.setActivityName(ActivitiesName.Agricultor);
		farmingActivity.setProductName(ProductsName.Feijão);;
		farmingActivity.setPeriod(Period.Mensal);
		farmingActivity.setAverageCash(3000);

		Address address = new Address();
		address.setUf("RN");
		address.setCity("Itaú");
		address.setZipCode("59855-000");
		address.setDistrict("Felicidade");
		address.setStreet("Rua José Domingos");
		address.setHouseNumber("36");
		address.setReference("Próximo ao Cantinho da Sopa");

		if (producer == null) {
			producer = new Producer();
			producer.setName("Vinícius Carneiro");
			producer.setNickname("Vina");
			producer.setCpf("080.934.345-77");
			producer.setBirthDate(new Date());
			producer.setPhone("(84)96666-6666");
			producer.setEmail("vina@gmail.com");
			producer.setPassword("123");
			producer.setManager(manager);
			producer.setFarmingActivity(farmingActivity);
			producer.setAddress(address);
			producerService.save(producer);
		}
	}
}
