package com.app.demo.dao;

import com.app.demo.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    int addEmployee(Employee emp);

    List<Employee> getALlEmployees();

    Employee getEmployee(int id);

    int deleteEmployee(int id);

    int updateEmployee(int id, Employee employee);
}
