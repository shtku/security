package com.shtku.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shtku.dao.ICommonDao;



@Repository(value = "commonDao")
public class CommonDao implements ICommonDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public <T> void saveOrUpdate(T t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}
	@Override
	public <T> void delete(T t) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public Object findById(Class a, Integer id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(a, id);
	}

	@Override
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		 Session session = sessionFactory.openSession();     
         StringBuffer hql = new StringBuffer();
         hql.append("from Users t where " + propertyName + "= '" + value.toString()+"'");
         Query query = session.createQuery(hql.toString());
	     return (T) query.uniqueResult();  
	}

}
