package com.joel.henz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joel.henz.model.Department;

@Repository //annotates classes at the persistence layer, which will act as a database repository.
public class DepartmentDao implements IDao<Department> {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Department getById(int id) {
		return (Department) sessionFactory.getCurrentSession().get(Department.class, id);
	}
	
	public Department getByDepartmentName(String departmentName) {
		return (Department) sessionFactory.getCurrentSession().get(Department.class, departmentName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Department")
				.list();
	}

	@Override
	public void save(Department t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(Department t) {
		sessionFactory.getCurrentSession().update(t);
	}

	@Override
	public void delete(Integer id) {
		Department department = (Department) sessionFactory.getCurrentSession().load(
				Department.class, id);
		if (department != null) {
			this.sessionFactory.getCurrentSession().delete(department);
		}
	}
}
