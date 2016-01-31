package com.chaitanya.masters.company.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chaitanya.masters.company.model.Branch;
import com.chaitanya.masters.company.model.Company;

@Repository
@Transactional
public class CompanyDAO implements ICompanyDAO{

	@Autowired
	SessionFactory sessionFactory;
	@SuppressWarnings("unchecked" )
	@Override
	public List<Branch> findBrachOnCompany(Company company) {
		Session session=sessionFactory.getCurrentSession();
		List<Branch> branchList=null;
		branchList=(List<Branch>)session.createCriteria(Branch.class)
				.add(Restrictions.eq("companyDetails.companyId",company.getCompanyId()))
								.list();
		return branchList;
	}

}
