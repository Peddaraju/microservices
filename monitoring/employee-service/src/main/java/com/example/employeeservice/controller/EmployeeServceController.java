package com.example.employeeservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.beans.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeServceController {

	public static final Map<Integer, Employee> empData = new HashMap<Integer, Employee>() {
		private static final long serialVersionUID = -3970206781360313502L;
		{
			put(111, new Employee(111, "Employee1"));
			put(222, new Employee(222, "Employee2"));
		}
	};
	
	@GetMapping("/findEmpDetails/{empId}")
	public Employee getEmployeeDetails(@PathVariable final int empId) {
		
		System.out.println("Getting Empoyee Details for:" + empId);
		
		Employee emp = empData.get(empId);
		if (emp == null) {
			emp = new Employee(0, "N/A");
		}
		
		return emp;
	}
}
