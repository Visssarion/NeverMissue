package dev.vissa.nevermissue.server.dao;

import java.util.List;

public interface DAO<E, K> {
	public void save(E entity);
	public void delete(E entity);
	public E get(K id);
	public List<E> getAll();
}
