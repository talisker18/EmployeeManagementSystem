package com.joel.henz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.henz.dao.EmployeeDao;
import com.joel.henz.model.Employee;

@Service //We mark beans with @Service to indicate that they're holding the business logic. Besides being used in the service layer, there isn't any other special use for this annotation.
@Transactional
public class EmployeeService implements IDaoService<Employee>{
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	@Transactional
	public void add(Employee t) {
		this.employeeDao.save(t);
	}

	@Override
	public List<Employee> getAll() {
		return this.employeeDao.getAll();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		this.employeeDao.delete(id);
	}

	@Override
	public Employee get(int id) {
		return this.employeeDao.getById(id);
	}

	@Override
	@Transactional
	public void update(Employee t) {
		this.employeeDao.update(t);
	}

}
