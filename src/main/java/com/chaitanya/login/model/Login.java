package com.chaitanya.login.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.chaitanya.employee.model.Employee;

@Entity
@Table(name="login_details")
public class Login {
	
	@Id @GeneratedValue
	@Column(name="login_id")
	private Long loginId;
	@OneToOne
	@PrimaryKeyJoinColumn
	private Employee employeeDetails;
	private String password;
	@Column(name="user_name")
	private String userName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loginId")
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	public Long getLoginId() {
		return loginId;
	}
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<UserRole> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Employee getEmployeeDetails() {
		return employeeDetails;
	}
	public void setEmployeeDetails(Employee employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

}
