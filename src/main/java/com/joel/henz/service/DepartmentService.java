package com.joel.henz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joel.henz.dao.DepartmentDao;
import com.joel.henz.model.Department;

@Service //We mark beans with @Service to indicate that they're holding the business logic. Besides being used in the service layer, there isn't any other special use for this annotation.
@Transactional
public class DepartmentService implements IDaoService<Department>{
	
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	@Transactional
	public void add(Department t) {
		this.departmentDao.save(t);
	}

	@Override
	public List<Department> getAll() {
		return this.departmentDao.getAll();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		/**
		 * these blocks are used if admin would be able to update / delete department. But this is not a requirement and therefore not implemented
		 * 
		 * Predicate<Regulation> predicate = FilterHelper.filterRegulationByDepartmentId(id);
		List<Regulation> listRegulation = FilterHelper.filterByPredicate(regulationDao.getAll(), predicate);
		
		for(Regulation reg: listRegulation) {
			regulationDao.delete(reg.getId());
		}
		
		//now update employee table. Set department = null for every employee that works for the deleted department
		Predicate<Employee> predicateEmployee = FilterHelper.filterEmployeeByDepartmentId(id);
		List<Employee> listEmployee = FilterHelper.filterByPredicate(employeeDao.getAll(), predicateEmployee);
		
		for(Employee emp: listEmployee) {
			emp.setDepartment(null);
			this.employeeDao.update(emp);
		}
		 * 
		 * 
		 * */

		this.departmentDao.delete(id);
	}

	@Override
	public Department get(int id) {
		return this.departmentDao.getById(id);
	}

	@Override
	@Transactional
	public void update(Department t) {
		this.departmentDao.update(t);
	}
}
