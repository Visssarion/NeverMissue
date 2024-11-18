package dev.vissa.nevermissue.server.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import dev.vissa.nevermissue.server.database.Database;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HibernateDAO<E, K> implements JpaDAO<E, K> {

	private Class<E> clazz;
	
	public HibernateDAO(Class<E> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void save(E entity) {
		System.out.println(Database.getCurrentSession());
		System.out.println(Database.getCurrentSession().getTransaction());
		Database.getCurrentSession().persist(entity);
	}

	@Override
	public void delete(E entity) {
		Database.getCurrentSession().remove(entity);
		
	}

	@Override
	public E get(K id) {
		return Database.getCurrentSession().get(clazz, id);
	}

	@Override
	public List<E> getAll() {
		Session session = Database.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
	    CriteriaQuery<E> cq = cb.createQuery(clazz);
	    Root<E> rootEntry = cq.from(clazz);
	    CriteriaQuery<E> all = cq.select(rootEntry);
	    Query<E> allQuery = session.createQuery(all);
	    return allQuery.getResultList();
	}

	@Override
	public CriteriaQuery<E> getCriteriaQuery() {
		CriteriaBuilder cb = Database.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<E> cr = cb.createQuery(clazz);
		Root<E> root = cr.from(clazz);
		return cr.select(root);
	}

	@Override
	public List<E> getCriteriaQueryResultList(CriteriaQuery<E> cq) {
		TypedQuery<E> query = Database.getCurrentSession().createQuery(cq);
		return query.getResultList();
	}

	@Override
	public E getCriteriaQuerySigleResult(CriteriaQuery<E> cq) {
		TypedQuery<E> query = Database.getCurrentSession().createQuery(cq);
		return query.getSingleResult();
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return Database.getCurrentSession().getCriteriaBuilder();
	}

}
