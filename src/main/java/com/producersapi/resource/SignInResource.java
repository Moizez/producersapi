package com.producersapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.model.Manager;
import com.producersapi.service.SignInService;

@RestController
@RequestMapping("/api")
public class SignInResource {

	@Autowired
	private SignInService service;
	
	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody Manager manager) {
		if (manager.getEmail() != null && manager.getPassword() != null) {
			Object obj = service.signIn(manager.getEmail(), manager.getPassword());
			return new ResponseEntity<Object>(obj, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
}
