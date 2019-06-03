package com.example.eureka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

	@GetMapping("/test/{str}")
	public String sayHello(@PathVariable String str) {
		return "Hello "+str;
	}
}
