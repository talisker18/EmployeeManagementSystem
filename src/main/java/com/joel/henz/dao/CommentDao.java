package com.joel.henz.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joel.henz.model.Comment;

@Repository //annotates classes at the persistence layer, which will act as a database repository.
public class CommentDao implements IDao<Comment>{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Comment getById(int id) {
		return (Comment) sessionFactory.getCurrentSession().get(Comment.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Comment")
				.list();
	}

	@Override
	public void save(Comment t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(Comment t) {
		sessionFactory.getCurrentSession().update(t);
	}

	@Override
	public void delete(Integer id) {
		Comment comment = (Comment) sessionFactory.getCurrentSession().load(
				Comment.class, id);
		if (comment != null) {
			this.sessionFactory.getCurrentSession().delete(comment);
		}
	}
	
	public Comment getByRegulationIdAndUserId(int regulationId, int userId) {
		@SuppressWarnings("unchecked")
		List<Comment> commentList = sessionFactory.getCurrentSession().createQuery("from Comment").list();
		
		Comment commentToReturn = null;
		for(Comment comment: commentList) {
			if(comment.getRegulation().getId() == regulationId && comment.getUserId() == userId) {
				commentToReturn =comment;
				break;
			}
		}
		
		return commentToReturn;
	}
}
