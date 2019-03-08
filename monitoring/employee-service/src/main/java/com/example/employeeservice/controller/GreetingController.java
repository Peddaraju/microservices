package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface GreetingController {

	@GetMapping("/greeting/{username}")
	String greeting(@PathVariable String username);
}
