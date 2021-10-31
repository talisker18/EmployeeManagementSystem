package com.joel.henz.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.joel.henz.model.Department;
import com.joel.henz.model.Employee;
import com.joel.henz.service.IDaoService;

public class EmployeeDepartmentDto {

	private int employeeId;
	private String firstName;
	private String lastName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String email;
	private String role;
	private String password;
	private String userName;
	private int departmentId;
	
	public EmployeeDepartmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeDepartmentDto(int employeeId, String firstName, String lastName, LocalDate dateOfBirth, String email,
			String role, String password, String userName, int departmentId) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.role = role;
		this.password = password;
		this.userName = userName;
		this.departmentId = departmentId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "EmployeeDepartmentDto [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", role=" + role + ", password=" + password
				+ ", userName=" + userName + ", departmentId=" + departmentId + "]";
	}
	
	public Employee createEmployee(IDaoService<Department> departmentService) {
		Department dep = departmentService.get(this.departmentId);
		Employee emp = new Employee(
				this.employeeId,
				this.firstName,
				this.lastName,
				this.dateOfBirth,
				this.email,
				this.role,
				this.password,
				this.userName,
				dep
		);
		
		return emp;
	}
}
