package com.chaitanya.employee.convertor;

import java.text.ParseException;

import com.chaitanya.employee.model.Employee;
import com.chaitanya.employee.model.EmployeeDTO;
import com.chaitanya.masters.company.model.Branch;
import com.chaitanya.masters.company.model.BranchDTO;
import com.chaitanya.masters.company.model.Company;
import com.chaitanya.utility.Convertor;
import com.chaitanya.utility.Validation;

public class EmployeeConvertor {
	
	public static EmployeeDTO setEmployeeToEmployeeDTO(Employee employee) throws ParseException{
		EmployeeDTO employeeDTO=null;
		if(Validation.validateForNullObject(employee)){
			employeeDTO=new EmployeeDTO(); 
			if(Validation.validateForZero(employee.getEmployeeId())){
				employeeDTO.setEmployeeId(employee.getEmployeeId());
			}
			if(Validation.validateForEmptyString(employee.getFirstName())){
				employeeDTO.setFirstName(employee.getFirstName());
			}
			if(Validation.validateForEmptyString(employee.getMiddleName())){
				employeeDTO.setMiddleName(employee.getMiddleName());			
			}
			if(Validation.validateForEmptyString(employee.getLastName())){
				employeeDTO.setLastName(employee.getLastName());
			}
			if(Validation.validateForEmptyString(employee.getEmailId())){
				employeeDTO.setEmailId(employee.getEmailId());
			}
			employeeDTO.setGender(employee.getGender());
						
			if(Validation.validateForNullObject(employee.getBranchDetails())){
				BranchDTO branchDTO=new BranchDTO();
				branchDTO.setBranchId(employee.getBranchDetails().getBranchId());
				employeeDTO.setBranchDTO(branchDTO);
			}
			if(Validation.validateForNullObject(employee.getCreatedBy())){
				employeeDTO.setCreatedBy(employee.getCreatedBy().getEmployeeId());
			}
			if(Validation.validateForNullObject(employee.getModifiedBy())){
				employeeDTO.setModifiedBy(employee.getModifiedBy().getEmployeeId());
			}
			if(Validation.validateForNullObject(employee.getCreatedDate())){
				employeeDTO.setCreatedDate(Convertor.calendartoString(employee.getCreatedDate()));
			}
			if(Validation.validateForNullObject(employee.getModifiedDate())){
				employeeDTO.setModifiedDate(Convertor.calendartoString(employee.getModifiedDate()));
			}
			employeeDTO.setStatus(Convertor.convetStatusToBool(employee.getStatus()));
		}
		return employeeDTO;
	}
	
	
	public static Employee setEmployeeDTOToEmployee(EmployeeDTO employeeDTO) throws ParseException
	{
		Employee employee=null;
		if(Validation.validateForNullObject(employeeDTO)){
			employee=new Employee();
			
			if(Validation.validateForNullObject(employeeDTO.getEmployeeId())){
				employee.setEmployeeId(employeeDTO.getEmployeeId());
			}
			employee.setFirstName(employeeDTO.getFirstName());
			employee.setMiddleName(employeeDTO.getMiddleName());
			employee.setLastName(employeeDTO.getLastName());
			employee.setEmailId(employeeDTO.getEmailId());
			employee.setGender(employeeDTO.getGender());
			if(Validation.validateForNullObject(employeeDTO.getBranchDTO())){
				Branch branchDetails=new Branch();
				branchDetails.setBranchId(employeeDTO.getBranchDTO().getBranchId());
				if(Validation.validateForNullObject(employeeDTO.getBranchDTO().getCompanyDTO()))
				{
					Company companyDetails=new Company();
					companyDetails.setCompanyId(employeeDTO.getBranchDTO().getCompanyDTO().getCompanyId());
					branchDetails.setCompanyDetails(companyDetails);
				}
				employee.setBranchDetails(branchDetails);
			}
			if(Validation.validateForZero(employeeDTO.getModifiedBy())){
				Employee modifiedBy=new Employee();
				modifiedBy.setEmployeeId(employeeDTO.getModifiedBy());
				employee.setModifiedBy(modifiedBy);
			}
			if(Validation.validateForZero(employeeDTO.getCreatedBy())){
				Employee createdBy=new Employee();
				createdBy.setEmployeeId(employeeDTO.getCreatedBy());
				employee.setModifiedBy(createdBy);
			}
			if(Validation.validateForNullObject(employeeDTO.getCreatedDate())){
				employee.setCreatedDate(Convertor.stringToCalendar(employeeDTO.getCreatedDate()));
			}
			if(Validation.validateForNullObject(employeeDTO.getModifiedDate())){
				employee.setModifiedDate(Convertor.stringToCalendar(employeeDTO.getModifiedDate()));
			}
			employee.setStatus(Convertor.convertStatusToChar(employeeDTO.getStatus()));
		}
		return employee;
	}
}
