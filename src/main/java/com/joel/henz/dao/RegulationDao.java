package com.joel.henz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joel.henz.model.Regulation;

@Repository //annotates classes at the persistence layer, which will act as a database repository.
public class RegulationDao implements IDao<Regulation> {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Regulation getById(int id) {
		return (Regulation) sessionFactory.getCurrentSession().get(Regulation.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Regulation> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Regulation")
				.list();
	}

	@Override
	public void save(Regulation t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(Regulation t) {
		sessionFactory.getCurrentSession().update(t);
	}

	@Override
	public void delete(Integer id) {
		Regulation regulation = (Regulation) sessionFactory.getCurrentSession().load(
				Regulation.class, id);
		if (regulation != null) {
			this.sessionFactory.getCurrentSession().delete(regulation);
		}
	}
}
