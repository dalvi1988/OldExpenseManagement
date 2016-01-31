package com.chaitanya.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chaitanya.employee.model.EmployeeDTO;
import com.chaitanya.employee.service.IEmployeeService;
import com.chaitanya.login.model.LoginUserDetails;
import com.chaitanya.masters.company.model.BranchDTO;
import com.chaitanya.masters.company.service.ICompanyService;
import com.chaitanya.utility.Validation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EmployeeController {

	@Autowired
	@Qualifier("employeeService")
	IEmployeeService employeeService;
	@Autowired
	ICompanyService companyService;
	
	@RequestMapping(value="employee",method=RequestMethod.GET)
	public ModelAndView getAllEmployee() throws JsonProcessingException{
		ModelAndView model=new ModelAndView();
		ObjectMapper mapper = new ObjectMapper();
		List<EmployeeDTO> employeeDTOList=null;
		List<BranchDTO> branchDTOList=null;
		LoginUserDetails user = (LoginUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(Validation.validateForNullObject(user.getLoginDTO().getEmployeeDTO())){
			employeeDTOList=new ArrayList<EmployeeDTO>();
			branchDTOList=new ArrayList<BranchDTO>();
			EmployeeDTO employeeDTO=user.getLoginDTO().getEmployeeDTO();
			if(Validation.validateForNullObject(employeeDTO.getBranchDTO().getCompanyDTO())){
				employeeDTOList=employeeService.findEmpployeeOnCompany(employeeDTO);
				branchDTOList=companyService.findBranchOnCompany(employeeDTO.getBranchDTO().getCompanyDTO());
			}
			if(Validation.validateCollectionForNullSize(employeeDTOList)){
				model.addObject("employeeList", mapper.writeValueAsString(employeeDTOList));
			}
			if(Validation.validateCollectionForNullSize(employeeDTOList)){
				model.addObject("branchList", mapper.writeValueAsString(branchDTOList));
			}
			zxcz
		}
		model.setViewName("master/employeeJSP");
		return model;
	}
}
