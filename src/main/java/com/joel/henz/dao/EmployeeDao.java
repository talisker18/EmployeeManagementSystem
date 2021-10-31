package com.joel.henz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joel.henz.model.Employee;

@Repository //annotates classes at the persistence layer, which will act as a database repository.
public class EmployeeDao implements IDao<Employee>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Employee getById(int id) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Employee")
				.list();
	}

	@Override
	public void save(Employee t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(Employee t) {
		sessionFactory.getCurrentSession().update(t);
	}

	@Override
	public void delete(Integer id) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().load(
				Employee.class, id);
		if (employee != null) {
			this.sessionFactory.getCurrentSession().delete(employee);
		}
		
	}

}
