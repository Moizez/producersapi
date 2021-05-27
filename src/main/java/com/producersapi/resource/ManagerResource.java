package com.producersapi.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.model.Manager;
import com.producersapi.model.Producer;
import com.producersapi.service.ManagerService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/managers")
public class ManagerResource extends Response<Manager> implements EntityResource<Manager> {

	@Autowired
	private ManagerService service;
	
	@Autowired
	private PasswordResource res;
	
	//Verifica se o email e CFP já exitem para outro Manager
	private boolean checkNotDuplicate(Manager entity) {
		Manager checkEmail = service.findByEmail(entity.getEmail());
		if(checkEmail != null) {
			if(!checkEmail.getId().equals(entity.getId())){ 
				System.out.println("e-mail repedito");
				return false;
			};
		};
		
		Manager checkCpf = service.findByCpf(entity.getCpf());
		if(checkCpf != null){
			if(!checkCpf.getId().equals(entity.getId())){ 
				System.out.println("CPF repedito");
				return false;
			}
		}
		System.out.println("Tudo Certo");
		return true;
	}

	@Override
	public ResponseEntity<?> save(Manager entity) {
		if(checkNotDuplicate(entity)) {
			service.save(entity);
			if (entity.getId() > 0) {
				try {
					res.SendEmail(entity.getEmail());
				} catch ( Exception e ) {
					System.out.println(e);
				};
			};		
			return new ResponseEntity<Manager>(entity, HttpStatus.CREATED);
		} 
		return ResponseEntity.badRequest().body("{\"message\": \"E-mail ou CPF já cadastrado para outro usuário!\" }");
	}

	@Override
	public ResponseEntity<List<Manager>> findAll() {
		return findAll(service);
	}

	@Override
	public ResponseEntity<?> findById(Integer id) {
		return findById(service, id);
	}

	@Override
	public ResponseEntity<?> updateById(Integer id, Manager entity) {
		entity.setId(id);
		System.out.println("updateById");
		if(checkNotDuplicate(entity)) {
			Optional<Manager> manager = service.findById(id);
			if (manager.isPresent()) {
				BeanUtils.copyProperties(entity, manager.get(), "id", "password");
				service.save(manager.get());
				return ResponseEntity.ok(manager.get());
			}	
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.badRequest().body("{\"message\": \"E-mail ou CPF já cadastrado para outro usuário!\" }");
	}

	@Override
	public ResponseEntity<?> deleteById(Integer id) {
		Optional<Manager> manager = service.findById(id);

		if (manager.isPresent()) {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ((BodyBuilder) ResponseEntity.notFound()).body("\"message\": \"Usuário não encontrato!\"");
	}
	
	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
		Manager manager = service.findByEmail(email);
		if(manager !=null) {
			return ResponseEntity.ok(manager);
		} else {
			return ((BodyBuilder) ResponseEntity.notFound()).body("\"message\": \"Usuário não encontrato!\"");
		}
		
	}
	
	@GetMapping("/findByCpf/{cpf}")
	public ResponseEntity<?> findByCpf(@PathVariable("cpf") String cpf) {
		Manager manager = service.findByCpf(cpf);
		if(manager !=null) {
			return ResponseEntity.ok(manager);
		} else {
			return ((BodyBuilder) ResponseEntity.notFound()).body("\"message\": \"Usuário não encontrato!\"");
		}
		
	}
	
}