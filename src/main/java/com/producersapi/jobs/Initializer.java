package com.producersapi.jobs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.producersapi.enums.ActivitiesName;
import com.producersapi.enums.Period;
import com.producersapi.model.Address;
import com.producersapi.model.FarmingActivity;
import com.producersapi.model.Manager;
import com.producersapi.model.Producer;
import com.producersapi.model.Product;
import com.producersapi.service.ManagerService;
import com.producersapi.service.ProducerService;
import com.producersapi.service.ProductService;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ProducerService producerService;

	@Autowired
	private ProductService productService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createManager();
		//createProducer();
		createProducts();
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
			manager.setCpf("727.094.870-47");
			manager.setBirthDate(new Date());
			manager.setPhone("(84)95555-5555");
			manager.setEmail("leo@gmail.com");
			manager.setPassword("123");
			manager.setAddress(address);
			manager.setTasks(null);
			managerService.save(manager);
		}
	}

	private void createProducer() {
		Producer producer = producerService.findByEmail("vina@gmail.com");
		Manager manager = managerService.findByEmail("leo@gmail.com");

		FarmingActivity farmingActivity = new FarmingActivity();
		farmingActivity.setActivityName(ActivitiesName.Agricultor);
		farmingActivity.setProducers(null);
		farmingActivity.setPeriod(Period.Mensal);
		farmingActivity.setAverageCash(3000);
		;

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
			producer.setCpf("855.036.400-20");
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

	public void createProducts() {

		String products[] = { "Batata", "Caju", "Camarão", "Castanha de Caju", "Feijão", "Filé de Peixe", "Goiaba",
				"Laranja", "Leite de Cabra", "Leite de Vaca", "Manga", "Mel", "Milho", "Peixe" };

		for (String label : products) {
			if (productService.findByLabel(label) == null) {
				Product product = new Product();
				product.setLabel(label);
				productService.save(product);
			}

		}

	}
}
