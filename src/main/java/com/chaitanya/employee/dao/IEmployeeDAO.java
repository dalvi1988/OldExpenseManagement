package com.chaitanya.employee.dao;

import java.util.List;

import com.chaitanya.employee.model.Employee;

public interface IEmployeeDAO {

	Employee add(Employee employee);

	List<Employee> findAll(Employee employee);

	
}
