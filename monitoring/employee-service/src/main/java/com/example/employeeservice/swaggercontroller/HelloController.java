package com.example.employeeservice.swaggercontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class HelloController {

	@ApiOperation(value = "getGreeting", notes="Get greeting",nickname = "getGreeting")
	@ApiResponses(value = {
		        @ApiResponse(code = 500, message = "Server error"),
		         @ApiResponse(code = 404, message = "Service not found"),
		        @ApiResponse(code = 200, message = "Successful retrieval",
		            response = String.class, responseContainer = "String") })
	@GetMapping("/swagger")
	public String sayHello() {
		return "Hi I am from Swagger Controller";
	}
}
