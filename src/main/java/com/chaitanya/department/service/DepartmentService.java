package com.chaitanya.department.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaitanya.Base.BaseDTO;
import com.chaitanya.Base.BaseDTO.ServiceStatus;
import com.chaitanya.department.convertor.DepartmentConvertor;
import com.chaitanya.department.dao.IDepartmentDAO;
import com.chaitanya.department.model.Department;
import com.chaitanya.department.model.DepartmentDTO;
import com.chaitanya.utility.Validation;

@Service("departmentService")
public class DepartmentService {

	@Autowired
	private IDepartmentDAO departmentDAO;
	private Logger logger= LoggerFactory.getLogger(DepartmentService.class);

	public BaseDTO addDepartment(BaseDTO baseDTO) {
		logger.debug("DepartmentService: addDepartment-Start");
		if(validateDepartmentMasterDTO(baseDTO)){
			throw new IllegalArgumentException("Object expected of DepartmentMasterDTO type.");
		}
		try{
			Department department=DepartmentConvertor.setDepartmentDTOToDepartment((DepartmentDTO)baseDTO);
			if (Validation.validateForNullObject(department)) {
				department=departmentDAO.add(department);
				if(Validation.validateForNullObject(department)){
					baseDTO=DepartmentConvertor.setDepartmentToDepartmentDTO(department);
					baseDTO.setServiceStatus(ServiceStatus.SUCCESS);
				}
			}
			else{
				baseDTO.setServiceStatus(ServiceStatus.BUSINESS_VALIDATION_FAILURE);
			}
		}
		catch(HibernateException e){
			baseDTO.setServiceStatus(ServiceStatus.SYSTEM_FAILURE);
			logger.error("Department Service: Exception",e);
		}
		catch(Exception e){
			baseDTO.setServiceStatus(ServiceStatus.SYSTEM_FAILURE);
			logger.error("Department Service: Exception",e);
		}
		logger.debug("DepartmentService: addDepartment-End");
		return baseDTO;
	}

	public List<DepartmentDTO> findAll() {
		List<DepartmentDTO> departmentDTOList = null;
		List<Department> departmentList = departmentDAO.findAll();
		if (Validation.validateCollectionForNullSize(departmentList)) {
			departmentDTOList = new ArrayList<DepartmentDTO>();
			for (Iterator<Department> iterator = departmentList.iterator(); iterator
					.hasNext();) {
				Department department = iterator.next();
				DepartmentDTO departmentDTO = DepartmentConvertor
						.setDepartmentToDepartmentDTO(department);
				departmentDTOList.add(departmentDTO);
			}
		}
		return departmentDTOList;
	}
	private boolean validateDepartmentMasterDTO(BaseDTO baseDTO) {
		return baseDTO == null  || !(baseDTO instanceof DepartmentDTO);
	}
}
