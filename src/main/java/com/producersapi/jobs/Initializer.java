package com.producersapi.jobs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.producersapi.model.Address;
import com.producersapi.model.Manager;
import com.producersapi.model.Product;
import com.producersapi.service.ManagerService;
import com.producersapi.service.ProductService;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ProductService productService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createManager();
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
