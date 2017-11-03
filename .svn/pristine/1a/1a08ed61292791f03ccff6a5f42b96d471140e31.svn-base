/**
* Copyright (c) DATANG MOBILE COMMUNICATIONS EQUIPMENT CO.,LTD.  1998-2014. 
* All rights reserved.
*/

package com.oio.wawj.dao.impl;

import java.util.List;

import javax.persistence.Entity;
import org.hibernate.Query;

import com.oio.wawj.bean.Function;
import com.oio.wawj.bean.RoleFunctions;
import com.oio.wawj.dao.RoleFunctionsDAO;




/**
 * ��Ҫ���ܣ�RoleFunction��ص�DAOʵ���࣬��Ҫ������RoleFunction��Ĳ�������
 * @author 
 */
@Entity
@SuppressWarnings("unchecked")
public class RoleFunctionsDAOImpl extends BaseDAOImpl<RoleFunctions, Short>
		implements RoleFunctionsDAO {
	/**
	 * ��ѯFunction�б���Ϣ
	 * @param id ������
	 * @return Function��Ϣ
	 */
	public List<Function> findFunctionNamesById(int id) {
		// _function.PARENT_FUNCTION_ID = 2
		String hql = "from Function s where s.parentFunctionId=?";
		Query q = getSession().createQuery(hql);
		q.setInteger(0, id);

		return q.list();
	}

	/**
	 * ��ѯRoleFunction�б���Ϣ
	 * @param id ������
	 * @return RoleFunction��Ϣ
	 */
	public List<RoleFunctions> findByRoleId(Short id) {
		// TODO Auto-generated method stub
		String hql = "from RoleFunctions s where s.id.roleId=?";
		Query q = getSession().createQuery(hql);
		q.setInteger(0, id);

		return q.list();
	}

	/**
	 * ��ѯ����Function�б���Ϣ
	 * @return Function��Ϣ
	 */
	public List<Function> findAllFunction() {
		String hql = "from Function s where s.levelId not in(0,1)";
		Query q = getSession().createQuery(hql);

		return q.list();
	}

	/**
	 * ��ѯFunction���ڵ��б���Ϣ
	 * @param id ������
	 * @return Function���ڵ��б���Ϣ
	 */
	public List<Function> getListByParentId(Long id){
		String hql = "from Function s where s.parentFunctionId="+id;
		Query q = getSession().createQuery(hql);

		return q.list();
	}

	/**
	 * ��ѯFunction����
	 * @param id ������
	 * @return Function�б���Ϣ
	 */
	public List<Function> getListByLevelId(Integer id){
		String hql = "from Function s where s.levelId="+id;
		Query q = getSession().createQuery(hql);

		return q.list();
	}
}