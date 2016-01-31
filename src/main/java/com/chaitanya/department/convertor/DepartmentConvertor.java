package com.chaitanya.department.convertor;

import java.text.ParseException;
import java.util.Calendar;

import com.chaitanya.department.model.Department;
import com.chaitanya.department.model.DepartmentDTO;
import com.chaitanya.employee.model.Employee;
import com.chaitanya.utility.Convertor;
import com.chaitanya.utility.Validation;

public class DepartmentConvertor {
	
	public static DepartmentDTO setDepartmentToDepartmentDTO(Department department){
		DepartmentDTO departmentDTO=null;
		if(Validation.validateForNullObject(department)){
			departmentDTO=new DepartmentDTO(); 
			departmentDTO.setDepartmentId(department.getDepartmentId());
			departmentDTO.setDepartmentName(department.getDepartmentName());
			departmentDTO.setDepartmentCode(department.getDepartmentCode());
			if(Validation.validateForNullObject(department.getCreatedBy())){
				departmentDTO.setCreatedBy(department.getCreatedBy().getEmployeeId());
			}
			if(Validation.validateForNullObject(department.getModifiedBy())){
				departmentDTO.setModifiedBy(department.getModifiedBy().getEmployeeId());
			}
			if(Validation.validateForNullObject(department.getCreatedDate())){
				departmentDTO.setCreatedDate(Convertor.calendartoString(department.getCreatedDate()));
			}
			if(Validation.validateForNullObject(department.getModifiedDate())){
				departmentDTO.setModifiedDate(Convertor.calendartoString(department.getModifiedDate()));
			}
			departmentDTO.setStatus(Convertor.convetStatusToBool(department.getStatus()));
		}
		return departmentDTO;
	}
	
	
	public static Department setDepartmentDTOToDepartment(DepartmentDTO departmentDTO) throws ParseException
	{
		Department department=null;
		if(Validation.validateForNullObject(departmentDTO)){
			department=new Department();
			if(Validation.validateForNullObject(departmentDTO.getDepartmentId())){
				department.setDepartmentId(departmentDTO.getDepartmentId());
			}
			if(Validation.validateForZero(departmentDTO.getModifiedBy())){
				Employee modifiedBy=new Employee();
				modifiedBy.setEmployeeId(departmentDTO.getModifiedBy());
				department.setModifiedBy(modifiedBy);
			}
			if(Validation.validateForZero(departmentDTO.getCreatedBy())){
				Employee createdBy=new Employee();
				createdBy.setEmployeeId(departmentDTO.getCreatedBy());
				department.setModifiedBy(createdBy);
			}
			if(Validation.validateForNullObject(departmentDTO.getCreatedDate())){
				department.setCreatedDate(Convertor.stringToCalendar(departmentDTO.getCreatedDate()));
			}
			if(Validation.validateForNullObject(departmentDTO.getModifiedDate())){
				department.setModifiedDate(Convertor.stringToCalendar(departmentDTO.getModifiedDate()));
			}
			department.setDepartmentCode(departmentDTO.getDepartmentCode());
			department.setDepartmentName(departmentDTO.getDepartmentName());
			department.setStatus(Convertor.convertStatusToChar(departmentDTO.getStatus()));
		}
		return department;
	}
}
