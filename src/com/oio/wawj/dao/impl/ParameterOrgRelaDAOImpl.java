package com.oio.wawj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.oio.wawj.bean.Parameter;
import com.oio.wawj.bean.ParameterOrgRela;
import com.oio.wawj.bean.TemplateOrgRela;
import com.oio.wawj.dao.ParameterOrgRelaDAO;
import com.oio.wawj.util.PageListData;

public class ParameterOrgRelaDAOImpl extends BaseDAOImpl<ParameterOrgRela,Short> implements ParameterOrgRelaDAO {

	public ParameterOrgRela getFindPORById(final Integer setId, final Integer parameterId) {
		return  (ParameterOrgRela) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
//						Long idd = Long.parseLong(id);
					
						String hql = "  from ParameterOrgRela por " +
								     " where  por.orgId =:setId and por.parameterId =:parameterId";		
						Query query = s.createQuery(hql);
						query.setInteger("setId", setId);
						query.setInteger("parameterId", parameterId);
						
						
						
						return query.list().size()==0?null:query.list().get(0);	

					}
				});
	}
	
	public TemplateOrgRela getFindTORById(final Integer setId, final Integer parameterId) {
		return  (TemplateOrgRela) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
//						Long idd = Long.parseLong(id);
						
						String hql = "  from TemplateOrgRela tor " +
								     " where  tor.orgId =:setId and tor.parameterId =:parameterId";		
						Query query = s.createQuery(hql);
						query.setInteger("setId", setId);
						query.setInteger("parameterId", parameterId);
						
						
						
						return query.list().size()==0?null:query.list().get(0);	
						
					}
				});
	}
	
	

}
