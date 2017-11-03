

package com.oio.wawj.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oio.wawj.dao.BaseDAO;
import com.oio.wawj.util.PageListData;




/**
 * 主要功能：Base相关的DAO实现类，主要包括对Base表的操作方法
 * @author 
 */
@Entity
public class BaseDAOImpl<T, PK extends Serializable> extends
		HibernateDaoSupport implements BaseDAO<T, PK> {

	static Logger logger = LoggerFactory.getLogger(BaseDAOImpl.class);

	/**
	 * 根据传入参数保存信息
	 * @param entity 参数名
	 * @return PK保存结果
	 */
	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		try {
			return (PK) getHibernateTemplate().save(entity);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
	}

	/**
	 * 根据传入参数保存或者更新信息
	 * @param entity 参数名
	 */
	public void saveOrUpdate(T entity) {
		try {
			getHibernateTemplate().merge(entity);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
	}
	
	/**
	 * 根据传入参数更新信息
	 * @param entity 参数名
	 */
	public void update(T entity) {
		try {
			getHibernateTemplate().update(entity);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
	}
	
	/**
	 * 根据传入参数删除信息
	 * @param entity 参数名
	 */
	public void delete(T entity) {
		try {
			getHibernateTemplate().delete(entity);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
	}
	
	/**
	 * 根据id删除某个对象
	 * @param entityClass 参数名
	 * @param id 参数名
	 */
	public void deleteById(Class<T> entityClass, PK id) {
		try {
			// Long lb=new Long((Long) id);
			// int ib=lb.intValue();
			getHibernateTemplate().delete(findById(entityClass, id));
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
	}
	
	/**
	 * 根据id加载某个对象
	 * @param entityClass 参数名
	 * @param id 参数名
	 */
	@SuppressWarnings("unchecked")
	public T findById(Class<T> entityClass, PK id) {
		try {
			// Long lb=new Long((Long) id);
			// int ib=lb.intValue();

			// entityClass.getClass().get

			return (T) getHibernateTemplate().get(entityClass, id);
		} catch (RuntimeException re) {
			logger.error("findById " + entityClass.getName() + " failed :{}",
					re);
			throw re;
		}
	}

//	// 根据id加载某个对象
//	@SuppressWarnings("unchecked")
//	public T findById(Class<T> entityClass, String id) {
//		try {
//			return (T) getHibernateTemplate().get(entityClass, id);
//		} catch (RuntimeException re) {
//			logger.error("findById " + entityClass.getName() + " failed :{}",
//					re);
//			throw re;
//		}
//	}

	/**
	 * 查找所有的对象
	 * @param entityClass 参数名
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> entityClass) {
		try {
			return getHibernateTemplate().loadAll(entityClass);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
	}
	
	/**
	 * 根据某个属性及其值精确或模糊查找对象
	 * @param entityClass 参数名
	 * @param propertyName 参数名
	 * @param value 参数名
	 * @param type 参数名
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value, int type) {
		String queryString = "";
		try {
			if (type == 1) {// type=1是精确查找
				queryString = "from " + entityClass.getName()
						+ " as model where model." + propertyName + "= ?";
			} else if (type == 2) {// type=2是模糊查找
				queryString = "from " + entityClass.getName()
						+ " as model where model." + propertyName + "like ?";
			}
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
	}
	
	/**
	 * 根据某个属性及其值精确或模糊查找对象
	 * @param entityClass 参数名
	 * @param propertyName 参数名
	 * @param value 参数名
	 * @param type 参数名
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(final Class<T> entityClass, final String propertyName,
			final Object value, int type, final int pageSize) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				String hql = "";
				hql = "from " + entityClass.getName()
						+ " model where model." + propertyName + "=" + value;

				Query query = arg0.createQuery(hql);
				int currentPage = 1;
				if (0 != pageSize) {
					query.setFirstResult(
							(currentPage == 0 ? 0 : currentPage - 1) * pageSize)
							.setMaxResults(pageSize);
				}
				List<T> data = query.list();
				return data;
			}
			
		});
	}
	
	/**
	 * 根据某个属性及其值精确或模糊查找对象，并区分不同状态
	 * @param entityClass 参数名
	 * @param propertyName 参数名
	 * @param value 参数名
	 * @param type 参数名
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value, int type, String statusName, String statusValue) {
		String queryString = "";
		try {
			if (type == 1) {// type=1是精确查找
				queryString = "from " + entityClass.getName()
						+ " as model where model." + propertyName + "= ?" 
						+ " and model."+ statusName + "="+ statusValue;
			} else if (type == 2) {// type=2是模糊查找
				queryString = "from " + entityClass.getName()
						+ " as model where model." + propertyName + "like ?"
						+ " and model."+ statusName + "="+ statusValue;;
			}
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
	}
	
	/**
	 * 根据某2个属性及其值精确或模糊查找对象，并区分不同状态
	 * @param entityClass 参数名
	 * @param propertyName 参数名
	 * @param value 参数名
	 * @param type 参数名
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(Class<T> entityClass, String propertyName, Object value, 
			String foreignPropertyName, Object foreignPropertyValue, int type, String statusName, String statusValue) {
		String queryString = "";
		try {
			if (type == 1) {// type=1是精确查找
				queryString = "from " + entityClass.getName()
						+ " as model where model." + propertyName + "= ?" 
						+ " and model."+ foreignPropertyName + "="+ foreignPropertyValue
						+ " and model."+ statusName + "="+ statusValue;
			} else if (type == 2) {// type=2是模糊查找
				queryString = "from " + entityClass.getName()
						+ " as model where model." + propertyName + "like ?"
						+ " and model."+ foreignPropertyName + "="+ foreignPropertyValue
						+ " and model."+ statusName + "="+ statusValue;;
			}
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
	}
	
	/**
	 * 根据某个属性及其值精确查找对象
	 * @param entityClass 参数名
	 * @param propertyName 参数名
	 * @param value 参数名
	 */
	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value) {
		return findByProperty(entityClass, propertyName, value, 1);
	}
	
	/**
	 * 根据hql语句及其查询参数，当前页数，每页显示的数目得到分页列表
	 * @param entityClass 参数名
	 * @param hql 参数名
	 * @param params 参数名
	 * @param currentPage 参数名
	 * @param pageSize 参数名
	 * @return 分页数据集，包含当页的数据记录及分页数据信息
	 */
	@SuppressWarnings("rawtypes")
	public PageListData findList(Class<T> entityClass, String hql,
			Object[] params, int currentPage, int pageSize) {
		PageListData listdata = null;
		try {
			Query query = getSession().createQuery(getCountsHql(hql));
			if (null != params && 0 != params.length) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			} else {
				logger.warn("参数为空");
			}
			int total = ((Long) query.uniqueResult()).intValue();
			logger.debug("总记录数:", total);
			query = getSession().createQuery(hql);
			if (null != params && 0 != params.length) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
					
				}
			}
			if (0 != pageSize) {
				query.setFirstResult(
						(currentPage == 0 ? 0 : currentPage - 1) * pageSize)
						.setMaxResults(total);
			}
			List data = query.list();			
			listdata = new PageListData(total, pageSize, currentPage, data);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw re;
		}
		return listdata;
	}

	
	/**
	 * 根据hql语句及其查询参数，当前页数，每页显示的数目得到分页列表
	 * @param hql 参数名
	 * @param currentPage 参数名
	 * @param pageSize 参数名
	 * @return 分页数据集，包含当页的数据记录及分页数据信息
	 */
	@SuppressWarnings("rawtypes")
	public PageListData findListThin(String hql, int currentPage, int pageSize) {
		PageListData listdata = null;
		try {
			Query query = getSession().createQuery(getCountsHql(hql));
			int total = ((Long) query.uniqueResult()).intValue();
			logger.debug("总记录数:", total);
			
			query = getSession().createQuery(hql);
			if (0 != pageSize) {
				query.setFirstResult(
						(currentPage == 0 ? 0 : currentPage - 1) * pageSize)
						.setMaxResults(pageSize);
			}
			List data = query.list();
			//System.out.println(data.size());
			listdata = new PageListData(total, pageSize, currentPage, data);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
		return listdata;
	}
	
	/**
	 * 根据hql语句及其查询参数，当前页数，每页显示的数目得到分页列表
	 * @param entityClass 参数名
	 * @param hql 参数名
	 * @param params 参数名
	 * @param currentPage 参数名
	 * @param pageSize 参数名
	 * @param fromCount 参数名
	 * @return 分页数据集，包含当页的数据记录及分页数据信息
	 */
	@SuppressWarnings("rawtypes")
	public PageListData findList(Class<T> entityClass, String hql,
			Object[] params, int currentPage, int pageSize, int fromCount) {
		PageListData listdata = null;
		try {
			Query query = getSession().createQuery(getCountsHql(hql, fromCount));
			if (null != params && 0 != params.length) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			} else {
				logger.warn("参数为空");
			}
			int total = ((Long) query.uniqueResult()).intValue();
			logger.debug("总记录数:", total);
			query = getSession().createQuery(hql);
			if (null != params && 0 != params.length) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			if (0 != pageSize) {
				query.setFirstResult(
						(currentPage == 0 ? 0 : currentPage - 1) * pageSize)
						.setMaxResults(pageSize);
			}
			List data = query.list();
			listdata = new PageListData(total, pageSize, currentPage, data);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
		return listdata;
	}
	/**
	 * 根据hql语句及其查询参数，当前页数，每页显示的数目得到分页列表
	 * @param hql 参数名
	 * @param currentPage 参数名
	 * @param pageSize 参数名
	 * @return 分页数据集，包含当页的数据记录及分页数据信息
	 */
	@SuppressWarnings("rawtypes")
	public PageListData findListForSql(String sql1, String sql, int currentPage, int pageSize) {
		PageListData listdata = null;
		try {
			SQLQuery query = getSession().createSQLQuery("select count(*) as total " + sql);
			query.addScalar("total", Hibernate.LONG);
			int total = ((Long) query.uniqueResult()).intValue();
			logger.debug("总记录数:", total); 
			query = getSession().createSQLQuery(sql1 + sql) ;
			if (0 != pageSize) {
				query.setFirstResult(
						(currentPage == 0 ? 0 : currentPage - 1) * pageSize).setMaxResults(pageSize);
			}
			List data = query.list();
			//System.out.println(data.size());
			listdata = new PageListData(total, pageSize, currentPage, data);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw re;
		}
		return listdata;
	}
	@SuppressWarnings("rawtypes")
	public PageListData findListForBillSql(String sql1, String sql, int currentPage, int pageSize) {
		PageListData listdata = null;
		try {
			SQLQuery query = getSession().createSQLQuery("select count(*) as total " + sql);
			query.addScalar("total", Hibernate.LONG);
			int total = ((Long) query.uniqueResult()).intValue();
			logger.debug("总记录数:", total);
			
			query = getSession().createSQLQuery(sql1 + sql) ;
			query.addScalar("custname",Hibernate.STRING )
			     .addScalar("appkey", Hibernate.STRING)
			     .addScalar("acms", Hibernate.STRING)
			     .addScalar("subts", Hibernate.STRING)
			     .addScalar("axb_minutes", Hibernate.INTEGER)
			     .addScalar("abcvoice", Hibernate.INTEGER)
			     .addScalar("i_voice_minutes", Hibernate.INTEGER)
			     .addScalar("i_package_fee", Hibernate.DOUBLE)
			     .addScalar("totalfee", Hibernate.DOUBLE)
			     .addScalar("provinces", Hibernate.STRING)
			     .addScalar("bill_month", Hibernate.STRING);
			if (0 != pageSize) {
				query.setFirstResult(
						(currentPage == 0 ? 0 : currentPage - 1) * pageSize);
			}
			List data = query.list();
			//System.out.println(data.size());
			listdata = new PageListData(total, pageSize, currentPage, data);

		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw re;
		}
		return listdata;
	}
	/**
	 * 为hql语句添加头
	 * @param hql 参数名
	 * @return hql查询语句
	 */
	String getCountsHql(String hql) {
		int index = hql.toUpperCase().indexOf("FROM");
		if (index != -1) {
			return "select count(*) " + hql.substring(index);
		}
		throw new RuntimeException("sql语句异常" + hql);
	}
	
	String getGroupByIntervalCountsHql(String hql) {
		int index = hql.toUpperCase().indexOf("FROM");
		System.out.println("rrrrrrrrrrrrrrrrrrr====="+hql);
		if (index != -1) {
			//String str1 = " select c.name as name from ( select c.name as name " + hql.substring(index)+ " ) as  b ";
			String str1 = "select c.name as name " + hql.substring(index);
			System.out.println("eeeeeeeeeeeeeee====="+str1);
			return str1;
		}
		throw new RuntimeException("sql语句异常" + hql);
	}
	
	/**
	 * 关联查询时使用
	 * @param hql 参数名
	 * @param fromCount 参数名
	 * @return hql查询语句
	 */
	String getCountsHql(String hql, int fromCount) {
		int index = -1;
		String hqlTemp = hql;
		for (int i = 0; i < fromCount ; i++){
			hqlTemp = hqlTemp.substring(hqlTemp.toUpperCase().indexOf("FROM")+4);
		}
		hqlTemp = " FROM " + hqlTemp;
		index = hqlTemp.toUpperCase().indexOf("FROM");
		if (index != -1) {
			return "select count(*) " + hqlTemp.substring(index);
		}
		throw new RuntimeException("sql语句异常" + hql);
	}
	
	/**
	 * 证件、密码校验
	 * @param param 参数名和参数值集合
	 * @return 大于0表示校验成功，否则失败
	 */
	public int checkPassword(Class<T> entityClass, String hql,
			Object[] params) {
		int total = -1;
		try {
			//查询sql
			Query query = getSession().createQuery(getCountsHql(hql));
			if (null != params && 0 != params.length) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			} else {
				logger.warn("参数为空");
			//结果记录数
			total = ((Long) query.uniqueResult()).intValue();
			}
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw re;
		}
		return total;
	}
	
	public boolean checkRepeat(Class<T> c, String s,
			Object o) {
		return checkRepeat(c, s, o, 0);
	}
	
	public boolean checkRepeat(Class<T> c, String s,
			Object o, int type) {
		String hql = "select m." + s + " from " + c.getName() + 
				" as m where m." + s + "= ?";
		if (type == 1) {
			hql +=  " and m.status in ('V', 'I')";
		}
		List<?> list = getHibernateTemplate().find(hql, o);
		if (list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 刷新
	 */
	public void flush() {
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
	}

}
