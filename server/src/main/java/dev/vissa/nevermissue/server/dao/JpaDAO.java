package dev.vissa.nevermissue.server.dao;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public interface JpaDAO<E, K> extends DAO<E, K> {
	public CriteriaQuery<E> getCriteriaQuery();
	public CriteriaBuilder getCriteriaBuilder();
	public List<E> getCriteriaQueryResultList(CriteriaQuery<E> cq);
	public E getCriteriaQuerySigleResult(CriteriaQuery<E> cq);
}
