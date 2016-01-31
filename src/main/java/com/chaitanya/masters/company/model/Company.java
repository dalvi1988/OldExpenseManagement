package com.chaitanya.masters.company.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.chaitanya.employee.model.Employee;

@Entity
@Table(name="company_mst")
public class Company {
	
	@Id @GeneratedValue
	@Column(name="company_id")
	private Long companyId;
	
	@Column(name="company_code",unique=true)
	private String companyCode;
	
	@Column(name="company_name",unique=true)
	private String companyName;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Employee createdBy;
	
	@Column(name="created_date")
	private Calendar createdDate;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Employee modifiedBy;
	
	@Column(name="modified_date")
	private Calendar modifiedDate;
	
	@Column(name="status")
	private Character status;
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Employee getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}
	public Calendar getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}
	public Employee getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Employee modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Calendar getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Calendar modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}

}
