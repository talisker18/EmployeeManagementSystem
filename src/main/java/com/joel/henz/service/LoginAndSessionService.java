package com.joel.henz.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.joel.henz.model.Employee;

@Service
public class LoginAndSessionService {
	
	public Employee validateUser(String userName, String password, EmployeeService employeeDaoService) {
		//check in database if there is an employee corresponding to login data
		//get all available employees from database
		List<Employee> list = employeeDaoService.getAll();
		for(Employee emp: list) {
			if(emp.getUserName().equals(userName)) {
				if(emp.getPassword().equals(password)) {
					return emp;
				}
			}
		}
		
		return null;
	}
	
	public boolean checkIfUserIsLoggedIn(HttpSession session) {
		return session.getAttribute("username") != null;
	}

}
