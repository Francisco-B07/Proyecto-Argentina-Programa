package persistence.commons;

import java.util.List;

import model.Atraccion;

@SuppressWarnings("unused")
public interface GenericDAO<T> {

	public T find(Integer id);
	public List<T> findAll();
	public int countAll();
	public int insert(T t);
	public int update(T t);
	public int delete(T t);
}
