
package com.oio.wawj.dao;

import java.util.List;

import com.oio.wawj.util.PageListData;





public interface BaseDAO<T,PK> {
	public PK save(T entity);
	public void update(T entity);
	public void delete(T entity);
	public void deleteById(Class<T> entityClass, PK id);
	public void saveOrUpdate(T entity);
	public List<T> findAll(Class<T> entityClass);
	public PageListData findList(Class<T> entityClass, String hql,Object[] params, int currentPage, int pageSize);
//	public PageListData select(Class<T> entityClass,String hql,int currentPage,int pageSize);
	public PageListData findListForSql(String sql1, String sql, int currentPage, int pageSize);
	public PageListData findList(Class<T> entityClass, String hql,Object[] params, int currentPage, int pageSize, int fromCount);
	public int checkPassword(Class<T> entityClass, String hql, Object[] params);
	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value);
	public List<T> findByProperty(Class<T> entityClass, String propertyName, 
			Object value, int type);
	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value, int type, String statusName, String statusValue);
	public List<T> findByProperty(Class<T> entityClass, String propertyName, Object value, 
			String foreignPropertyName, Object foreignPropertyValue, int type, String statusName, String statusValue);
	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value, int type, int pageSize);
	public T findById(Class<T> entityClass, PK id);
//	public T findById(Class<T> entityClass, String id);
	public boolean checkRepeat(Class<T> c, String s,
			Object o);
	public boolean checkRepeat(Class<T> c, String s,
			Object o, int type);
	public void flush();
	public PageListData findListThin(String hql, int currentPage, int pageSize);
	public PageListData findListForBillSql(String sql1, String sql, int currentPage, int pageSize) ;
}