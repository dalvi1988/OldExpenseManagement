package com.chaitanya.Base;

import java.io.Serializable;

public class BaseDTO implements Serializable{
	public enum Command{
		ADD,UPDATE,DELETE
	}
	
	public enum ServiceStatus{
		SUCCESS,SUCCESS_WITH_NO_DATA,FAILURE,BUSINESS_VALIDATION_FAILURE,SYSTEM_FAILURE
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long createdBy;
	private String createdDate;
	private Long modifiedBy;
	private String modifiedDate;
	private Boolean status;
	private Command command; 
	private ServiceStatus serviceStatus;
	private StringBuilder message;
	
	public StringBuilder getMessage() {
		return message;
	}
	public void setMessage(StringBuilder message) {
		this.message = message;
	}
	public ServiceStatus getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(ServiceStatus serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Command getCommand() {
		return command;
	}
	public void setCommand(Command command) {
		this.command = command;
	}

}
