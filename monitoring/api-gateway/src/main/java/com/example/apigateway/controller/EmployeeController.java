package com.example.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class EmployeeController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/empDetails/{empId}")
	@HystrixCommand(fallbackMethod = "fallbackMethod")
	public String getStudents(@PathVariable int empId) {

		System.out.println("Getting Employee details for " + empId);

		ResponseEntity<String> responseEntity = restTemplate.exchange("http://EMPLOYEE-SERVICE/api/findEmpDetails/{empId}", 
				HttpMethod.GET, null,
				new ParameterizedTypeReference<String>() {
				}, empId);
		
		String response = responseEntity.getBody();

		System.out.println("Response Body:" + response);

		return "Employee Id -  " + empId + " [ Employee Details " + response + " ]";
	}

	public String fallbackMethod(int empId) {

		return "Fallback response:: No employee details available temporarily";
	}
}
