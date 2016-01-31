package com.chaitanya.masters.company.dao;

import java.util.List;

import com.chaitanya.masters.company.model.Branch;
import com.chaitanya.masters.company.model.Company;

public interface ICompanyDAO {
	public List<Branch> findBrachOnCompany(Company company);
}
