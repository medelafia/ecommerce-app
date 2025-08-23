package dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
	public Optional<T> getById(int id ); 
	public List<T> getAll() ; 
	public void save(T t) ; 
	public void deleteById(int id) ; 
	public void update(T t); 
	public Long getCount() ; 
	public List<T> getPage(int page , int size) ; 
}
