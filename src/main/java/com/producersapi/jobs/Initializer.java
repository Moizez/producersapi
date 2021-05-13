package com.producersapi.jobs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.producersapi.model.ActivityName;
import com.producersapi.model.Address;
import com.producersapi.model.Manager;
import com.producersapi.model.Product;
import com.producersapi.service.ActivityNameService;
import com.producersapi.service.ManagerService;
import com.producersapi.service.ProductService;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ActivityNameService activityNameService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createManager();
		createProducts();
		createActivities();
		System.out.println("----- Usuários Criados com Sucesso! -----");
	}
	
	
	
	private void createManager() {
		
		Manager manager = managerService.findByEmail("leo@gmail.com");
		Manager manager2 = managerService.findByEmail("thalitapolyana@hotmail.com");
		Manager manager3 = managerService.findByEmail("bandeiraalisson@hotmail.com");
		Manager manager4 = managerService.findByEmail("enbessa86@hotmail.com");

		Address address = new Address();
		Address address2 = new Address();
		Address address3 = new Address();
		Address address4 = new Address();
		
		address.setUf("RN");
		address.setCity("Itaú");
		address.setZipCode("59855-000");
		address.setDistrict("Centro");
		address.setStreet("Rua Cleofas Nunes");
		address.setHouseNumber("74");
		address.setReference("Próximo ao espetinho do Léo");
		
		address2.setUf("RN");
		address2.setCity("Itaú");
		address2.setZipCode("59855-000");
		address2.setDistrict("Centro");
		address2.setStreet("Rua Cleofas Nunes");
		address2.setHouseNumber("74");
		address2.setReference("Prefeitura Municipal de Itaú");
		
		address3.setUf("RN");
		address3.setCity("Itaú");
		address3.setZipCode("59855-000");
		address3.setDistrict("Centro");
		address3.setStreet("Rua Cleofas Nunes");
		address3.setHouseNumber("74");
		address3.setReference("Prefeitura Municipal de Itaú");
		
		address4.setUf("RN");
		address4.setCity("Itaú");
		address4.setZipCode("59855-000");
		address4.setDistrict("Centro");
		address4.setStreet("Rua Cleofas Nunes");
		address4.setHouseNumber("74");
		address4.setReference("Prefeitura Municipal de Itaú");

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
		
		if (manager2 == null) {
			manager2 = new Manager();
			manager2.setName("Thalita Polyana Monteiro Araújo");
			manager2.setNickname("Thalita");
			manager2.setCpf("067.507.070-84");
			manager2.setBirthDate(new Date());
			manager2.setPhone("(84)95555-5555");
			manager2.setEmail("thalitapolyana@hotmail.com");
			manager2.setPassword("admin87");
			manager2.setAddress(address2);
			manager2.setTasks(null);
			managerService.save(manager2);
		}
		
		if (manager3 == null) {
			manager3 = new Manager();
			manager3.setName("Alisson Gama Bandeira");
			manager3.setNickname("Alisson");
			manager3.setCpf("244.217.000-02");
			manager3.setBirthDate(new Date());
			manager3.setPhone("(84)95555-5555");
			manager3.setEmail("bandeiraalisson@hotmail.com");
			manager3.setPassword("admin97");
			manager3.setAddress(address3);
			manager3.setTasks(null);
			managerService.save(manager3);
		}
		
		if (manager4 == null) {
			manager4 = new Manager();
			manager4.setName("Ericka Natalia Bessa");
			manager4.setNickname("Ericka");
			manager4.setCpf("067.507.070-84");
			manager4.setBirthDate(new Date());
			manager4.setPhone("(84)95555-5555");
			manager4.setEmail("enbessa86@hotmail.com");
			manager4.setPassword("admin86");
			manager4.setAddress(address4);
			manager4.setTasks(null);
			managerService.save(manager4);
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
	
	public void createActivities() {

		String activities[] = { "Agricultor", "Pescador", "Leiteiro" };

		for (String label : activities) {
			if (activityNameService.findByLabel(label) == null) {
				ActivityName activity = new ActivityName();
				activity.setLabel(label);
				activityNameService.save(activity);
			}

		}

	}
}
