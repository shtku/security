package com.shtku.dao;



public interface ICommonDao {
	
	public <T> void saveOrUpdate(T t);
	
	public <T> void delete(T t);
	
	public Object findById(Class a ,Integer id);
	
	public <T> T findUniqueByProperty(Class<T> entityClass,final String propertyName,final Object value);    
   
}
