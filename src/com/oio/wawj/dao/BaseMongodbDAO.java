
package com.oio.wawj.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;


	public interface BaseMongodbDAO<T,PK> {

		/**
		 * ��Ӷ���
		 * @author 
		 */
		public void insert(Object entity);

		/**
		 * ���ID���Ҷ���
		 * @author 
		 */
		public T findOne(String id, Class<T> entity);

		public List<T> findByRegex(Class<T> entityClass, String regex);

		/**
		 * ɾ��ָ����ID����
		 * @author 
		 */
		public void removeOne(String id, Class<T> entity);

		/**
		 * ͨ��ID�ҵ����޸�
		 * @author 
		 */
		public void findAndModify(String id);

		public List<T> findAll(Class<T> entity);

		/**
		 * �޸�ʵ��
		 */
		public void updateEntity(Object entity);
		
		/**
		 * ������ѯ
		 * @param <T>
		 * @param criteria
		 * @param entity
		 * @return
		 */
		public T findEntityByCriteria(Criteria criteria,Class<T> entity);
}