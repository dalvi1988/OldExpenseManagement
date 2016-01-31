package com.chaitanya.masters.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaitanya.Base.BaseDTO;
import com.chaitanya.masters.company.dao.ICompanyDAO;
import com.chaitanya.masters.company.model.Branch;
import com.chaitanya.masters.company.model.BranchDTO;
import com.chaitanya.masters.company.model.Company;
import com.chaitanya.masters.company.model.CompanyDTO;
import com.chaitanya.utility.Validation;

@Service("companyService")
public class CompanyService implements ICompanyService {
	@Autowired
	ICompanyDAO companyDAO;
	@Override
	public List<BranchDTO> findBranchOnCompany(BaseDTO baseDTO) {
		if(validateCompanyBrachMasterDTO(baseDTO)){
			throw new IllegalArgumentException("Object expected of CompanyDTO or BranchDTO type.");
		}
		CompanyDTO companyDTO=(CompanyDTO) baseDTO;
		Company company=new Company();
		company.setCompanyId(companyDTO.getCompanyId());
		List<Branch> branchList=companyDAO.findBrachOnCompany(company);
		List<BranchDTO> branchDTOList=null;
		if(Validation.validateCollectionForNullSize(branchList)){
			branchDTOList=new ArrayList<BranchDTO>();
			for(Branch branch:branchList){
				BranchDTO branchDTO=new BranchDTO();
				branchDTO.setBranchId(branch.getBranchId());
				branchDTO.setBranchName(branch.getBranchName());
				branchDTO.setBranchCode(branch.getBranchCode());
				branchDTOList.add(branchDTO);
			}
		}
		
		return branchDTOList;
	}
	
	private boolean validateCompanyBrachMasterDTO(BaseDTO baseDTO) {
		return baseDTO == null  || !(baseDTO instanceof CompanyDTO);
	}
}
