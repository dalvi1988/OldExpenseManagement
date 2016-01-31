package com.chaitanya.department.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chaitanya.department.model.Department;

@Repository
@Transactional
public class DepartmentDAO implements IDepartmentDAO{

	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Department add(Department department){
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(department);
		return department;
	}

	@Override
	public List<Department> findAll() {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Department> departmentList=(List<Department>)session.createCriteria(Department.class)
				.list();
		return departmentList;
	}
}
