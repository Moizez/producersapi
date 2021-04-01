package com.producersapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producersapi.model.Manager;
import com.producersapi.model.Producer;

@Service
public class SignInService {

	@Autowired
	private ProducerService producerService;

	@Autowired
	private ManagerService managerService;

	public Object signIn(String email, String password) {

		Optional<Producer> producer = producerService.onSignIn(email, password);
		Optional<Manager> manager = managerService.onSignIn(email, password);

		if (producer.isPresent()) {
			return producer;

		} else if (manager.isPresent()) {
			return manager;

		} else {
			return null;
		}
	}
}
