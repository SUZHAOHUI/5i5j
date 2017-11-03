/**
* Copyright (c) REALSTONE CO.,LTD.  1998-2014. 
* All rights reserved.
*/
package com.oio.wawj.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oio.wawj.dao.BaseMongodbDAO;
import com.oio.wawj.util.PageListData;


public class BaseMongodbDAOImpl<T, PK extends Serializable> extends
HibernateDaoSupport implements BaseMongodbDAO<T, PK> {

	static Logger logger = LoggerFactory.getLogger(BaseMongodbDAOImpl.class);
	@Autowired
	private MongoTemplate mongoTemplate;

    /***
     * 查找所有对象；
     */
	@Override
	public List<T> findAll(Class<T> entity) {
		return mongoTemplate.findAll(entity);
	}

	/**
	 * 
	 */
	public void findAndModify(String id) {
		//mongoTemplate.find(new Query(Criteria.where("id").is(id)), new Update().inc("age", 3));
	}

	@Override
	public List<T> findByRegex(Class<T> entityClass, String regex) {
		 Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);   
	      Criteria criteria = new Criteria("name").regex(pattern.toString());   
	        return mongoTemplate.find(new Query(criteria), entityClass);   

	}

	/***
	 * 根据ID查找对象；
	 */
	@Override
	public T findOne(String id,Class<T> entity) {
		 return (T) mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), entity);   
	}

	/**
	 * 通过参数查询数据
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public PageListData findList(Class<T> entityClass, String hql,
			Map<String, Object> params, Map<String, Object> timeParam, int currentPage, int pageSize) {
		PageListData listdata = null;
		try {
			Criteria criteria = new Criteria();
			Query query = new Query();
			Iterator it = params.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry e=(Map.Entry)it.next();
				criteria.where(e.getKey().toString()).is(e.getValue());
			}
			if(timeParam.size() > 0){
				Object timeParams[] = timeParam.values().toArray();
				criteria.where("CREATE_DATE").gte(timeParams[0]).lte(timeParams[1]);
			}
			query.addCriteria(criteria);
			int skip = (currentPage-1)*pageSize;
			Long total = mongoTemplate.count(query, entityClass);
			query.skip(skip);// skip相当于从那条记录开始  
			query.limit(pageSize);// 从skip开始,取多少条记录
			List dataList = mongoTemplate.find(query, entityClass);
			listdata = new PageListData(total.intValue(), pageSize, currentPage, dataList);
		} catch (RuntimeException re) {
			logger.error("***Error: ", re);
		}
		return listdata;
	}
	
	/***
	 * 添加一个实体；
	 */
	@Override
	public void insert(Object entity) {
		mongoTemplate.insert(entity);   
	}


	/***
	 * 根据ID删除一个对象；
	 */
	@Override
	public void removeOne(String id,Class<T> entity) {
		Criteria criteria = Criteria.where("id").in(id);   
        if(criteria != null){   
             Query query = new Query(criteria);   
             if(query != null && mongoTemplate.findOne(query, entity) != null)   
                 mongoTemplate.remove(mongoTemplate.findOne(query, entity));   
        }   
	}

	/***
	 * 更新实体；
	 */
	@Override
	public void updateEntity(Object entity) {
		mongoTemplate.save(entity);
	}

	/***
	 * 根据Criteria查询一个实体；
	 */
	@Override
	public T findEntityByCriteria(Criteria criteria, Class<T> entity) {
		Query query=new Query();
		query.addCriteria(criteria);
		return  mongoTemplate.findOne(query, entity);
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
}
