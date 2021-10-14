package com.app.demo.service;

import com.app.demo.dao.EmployeeDAO;
import com.app.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public int addEmployee(Employee emp) {
        return employeeDAO.addEmployee(emp);
    }

    public List<Employee> getAllEmployess() {
        return employeeDAO.getALlEmployees();
    }

    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    public int deleteEmployee(int id) {
        return employeeDAO.deleteEmployee(id);
    }

    public int updateEmployee(int id, Employee employee) {
        return employeeDAO.updateEmployee(id, employee);
    }
}
