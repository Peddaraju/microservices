package com.app.demo.dao;

import com.app.demo.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    List<Employee> employees = new ArrayList<>();

    @Override
    public int addEmployee(Employee emp) {
        employees.add(emp);
        return 1;
    }

    @Override
    public List<Employee> getALlEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployee(int id) {

        for(Employee employee : employees) {
            if(id == employee.getEmpId()) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public int deleteEmployee(int id) {
        Employee deleteEmployee = null;
        for(Employee employee: employees) {
            if(employee.getEmpId() == id) {
                deleteEmployee = employee;
            }
        }
        if(deleteEmployee != null) {
            employees.remove(deleteEmployee);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateEmployee(int id, Employee employee) {
        int index = -1;
        for(Employee employee1: employees) {
            index++;
            if(employee1.getEmpId() == id) {
                employees.set(index, employee);
                return 1;
            }

        }
        return 0;
    }
}
