package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingControllerImpl implements GreetingController {

	@Override
	public String greeting(String username) {
		return String.format("Hello %s!\n", username);
	}

	
	
}
