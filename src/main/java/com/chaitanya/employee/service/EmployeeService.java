package com.chaitanya.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaitanya.Base.BaseDTO;
import com.chaitanya.Base.BaseDTO.ServiceStatus;
import com.chaitanya.employee.convertor.EmployeeConvertor;
import com.chaitanya.employee.dao.IEmployeeDAO;
import com.chaitanya.employee.model.Employee;
import com.chaitanya.employee.model.EmployeeDTO;
import com.chaitanya.utility.Validation;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	IEmployeeDAO employeeDAO;
	
	@Override
	public List<EmployeeDTO> findEmpployeeOnCompany(BaseDTO baseDTO) {
		if(validateEmployeeMasterDTO(baseDTO)){
			throw new IllegalArgumentException("Object expected of EmployeeMasterDTO type.");
		}
		
		List<EmployeeDTO> employeeDTOList = null;
		try{
			EmployeeDTO employeeDTO=(EmployeeDTO)baseDTO;
			if(Validation.validateForNullObject(employeeDTO)){
				Employee employee=EmployeeConvertor.setEmployeeDTOToEmployee(employeeDTO);
				List<Employee> employeeList = employeeDAO.findAll(employee);
				if (Validation.validateCollectionForNullSize(employeeList)) {
					employeeDTOList = new ArrayList<EmployeeDTO>();
					for (Employee emp:employeeList) {
						EmployeeDTO departmentDTO = EmployeeConvertor.setEmployeeToEmployeeDTO(emp);
						employeeDTOList.add(departmentDTO);
					}
					baseDTO.setServiceStatus(ServiceStatus.SUCCESS);
				}
				else{
					baseDTO.setServiceStatus(ServiceStatus.SUCCESS_WITH_NO_DATA);
				}
			}
			
		}
		catch(Exception e){
			
		}
		return employeeDTOList;
	}
	
	private boolean validateEmployeeMasterDTO(BaseDTO baseDTO) {
		return baseDTO == null  || !(baseDTO instanceof EmployeeDTO);
	}
	
}
