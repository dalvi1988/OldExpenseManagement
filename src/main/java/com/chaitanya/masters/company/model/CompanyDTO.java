package com.chaitanya.masters.company.model;

import com.chaitanya.Base.BaseDTO;

public class CompanyDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	private Long companyId;
	private String companyCode;
	private String companyname;
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
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

}
