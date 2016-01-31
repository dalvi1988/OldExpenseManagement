package com.chaitanya.masters.company.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.chaitanya.employee.model.Employee;

@Entity
@Table(name="branch_mst")
public class Branch {
	
	@Id @GeneratedValue
	@Column(name="branch_id")
	private Long branchId;
	
	@Column(name="branch_code",unique=true)
	private String branchCode;
	
	@Column(name="branch_name",unique=true)
	private String branchName;
	
	@ManyToOne
	@JoinColumn(name="company_Id")
	private Company companyDetails;
	
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
	
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	public Company getCompanyDetails() {
		return companyDetails;
	}
	public void setCompanyDetails(Company companyDetails) {
		this.companyDetails = companyDetails;
	}

}
