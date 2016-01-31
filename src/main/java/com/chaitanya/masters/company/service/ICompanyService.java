package com.chaitanya.masters.company.service;

import java.util.List;

import com.chaitanya.Base.BaseDTO;
import com.chaitanya.masters.company.model.BranchDTO;

public interface ICompanyService {

	List<BranchDTO> findBranchOnCompany(BaseDTO baseDTO);

}
