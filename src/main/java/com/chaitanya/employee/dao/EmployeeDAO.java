package com.chaitanya.employee.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chaitanya.employee.model.Employee;
import com.chaitanya.utility.Validation;

@Repository
@Transactional
public class EmployeeDAO implements IEmployeeDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Employee add(Employee employee){
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(employee);
		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAll(Employee employee) {
		Session session=sessionFactory.getCurrentSession();
		List<Employee> employeeList=null;
		if(Validation.validateForNullObject(employee.getBranchDetails().getCompanyDetails())){
			employeeList=(List<Employee>)session.createCriteria(Employee.class)
										.createAlias("branchDetails.companyDetails", "company",JoinType.LEFT_OUTER_JOIN)
										.add(Restrictions.eq("company.companyId", employee.getBranchDetails().getCompanyDetails().getCompanyId()))
										.list();
		}
		return employeeList;
	}
	
}
