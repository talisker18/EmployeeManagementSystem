package com.joel.henz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joel.henz.dao.CommentDao;
import com.joel.henz.model.Comment;

@Service //We mark beans with @Service to indicate that they're holding the business logic. Besides being used in the service layer, there isn't any other special use for this annotation.
@Transactional
public class CommentService implements IDaoService<Comment> {
	
	@Autowired
	private CommentDao commentDao;

	@Override
	@Transactional
	public void add(Comment t) {
		this.commentDao.save(t);
	}

	@Override
	public List<Comment> getAll() {
		return this.commentDao.getAll();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		this.commentDao.delete(id);
	}

	@Override
	public Comment get(int id) {
		return this.commentDao.getById(id);
	}

	@Override
	@Transactional
	public void update(Comment t) {
		this.commentDao.update(t);
	}
	
	public Comment getByRegulationIdAndUserId(int regulationId, int userId) {
		return this.commentDao.getByRegulationIdAndUserId(regulationId, userId);
	}
}
