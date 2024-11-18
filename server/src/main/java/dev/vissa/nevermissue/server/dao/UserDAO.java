package dev.vissa.nevermissue.server.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import dev.vissa.nevermissue.server.database.Database;
import dev.vissa.nevermissue.shared.entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class UserDAO implements DAO<User, Integer>{
	
	
	@Override
	public void save(User entity) {
		Database.getCurrentSession().persist(entity);
	}

	@Override
	public void delete(User entity) {
		Database.getCurrentSession().remove(entity);
		
	}

	@Override
	public User get(Integer id) {
		return Database.getCurrentSession().get(User.class, id);
	}

	@Override
	public List<User> getAll() {
		Session session = Database.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(User.class);
	    Root<User> rootEntry = cq.from(User.class);
	    CriteriaQuery<User> all = cq.select(rootEntry);
	    Query<User> allQuery = session.createQuery(all);
	    return allQuery.getResultList();	}

}
