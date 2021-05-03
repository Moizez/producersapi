package com.producersapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.model.User;
import com.producersapi.service.SignInService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class SignInResource {

	@Autowired
	private SignInService service;

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody User user) {
		if (user.getEmail() != null && user.getPassword() != null) {
			Object obj = service.signIn(user.getEmail(), user.getPassword());
			if (obj != null) {
				return new ResponseEntity<Object>(obj, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
}