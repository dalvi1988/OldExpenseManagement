package com.chaitanya.department.dao;

import java.util.List;

import com.chaitanya.department.model.Department;


public interface IDepartmentDAO {

	public Department add(Department department);
	
	public List<Department> findAll();
	
}
