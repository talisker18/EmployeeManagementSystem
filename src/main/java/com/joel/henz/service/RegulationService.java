package com.joel.henz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joel.henz.dao.RegulationDao;
import com.joel.henz.model.Regulation;

@Service //We mark beans with @Service to indicate that they're holding the business logic. Besides being used in the service layer, there isn't any other special use for this annotation.
@Transactional
public class RegulationService implements IDaoService<Regulation> {
	@Autowired
	private RegulationDao regulationDao;

	@Override
	@Transactional
	public void add(Regulation t) {
		this.regulationDao.save(t);
	}

	@Override
	public List<Regulation> getAll() {
		return this.regulationDao.getAll();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		this.regulationDao.delete(id);
	}

	@Override
	public Regulation get(int id) {
		return this.regulationDao.getById(id);
	}

	@Override
	@Transactional
	public void update(Regulation t) {
		this.regulationDao.update(t);
	}
}
