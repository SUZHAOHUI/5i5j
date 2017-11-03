package com.oio.wawj.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.oio.wawj.bean.UserAcmsRela;
import com.oio.wawj.dao.NumberDAO;
import com.oio.wawj.dao.StaffDAO;
import com.oio.wawj.dao.UserAcmsRelaDAO;
import com.oio.wawj.service.StaffService;
import com.oio.wawj.service.UserAcmsRelaService;
import com.oio.wawj.util.PageListData;




/**
 * 锟斤拷要锟斤拷锟杰ｏ拷Acms锟斤拷氐锟絪ervice锟接口ｏ拷锟斤拷要锟斤拷锟节达拷锟斤拷业锟斤拷锟竭硷拷锟斤拷锟斤拷锟斤拷锟斤拷dao锟斤拷牟锟斤拷锟斤拷锟斤拷锟�
 * @author 
 */
@Entity
public class UserAcmsRelaServiceImpl implements UserAcmsRelaService {
	@ManyToOne
	private UserAcmsRelaDAO dao;
	
	public UserAcmsRelaDAO getDao() {
		return dao;
	}

	public void setDao(UserAcmsRelaDAO dao) {
		this.dao = dao;
	}

	/**
	 * 锟斤拷锟斤拷锟铰�
	 * @param UserAcmsRela 要锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	public void save(UserAcmsRela sysUserAcmsRela) {
		dao.save(sysUserAcmsRela);
	}
   


	

	/**
	 * 删锟斤拷锟铰�
	 * @param id 要删锟斤拷锟斤拷锟斤拷
	 */
	public void deleteById(Integer id) {
		dao.deleteById(UserAcmsRela.class, id);
	}


	
	/**
	 * 锟斤拷锟铰硷拷录
	 * @param simc 要锟斤拷锟铰碉拷锟斤拷锟�
	 */
	public void update(UserAcmsRela sysUserAcmsRela) {
		dao.saveOrUpdate(sysUserAcmsRela);
	}
	

	


	/**
	 * 锟斤拷荽锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询SysRole锟叫憋拷
	 * @param id 
	 * @return SysRole锟叫憋拷锟斤拷锟较�
	 */
	public UserAcmsRela findById(Integer id) {

	//	UserAcmsRela sysUserAcmsRela = dao.findById(UserAcmsRela.class, id);
		return dao.findById(UserAcmsRela.class, id);
	}


    public List<UserAcmsRela> findbyAcmsId(Integer acmsId){
    	List<UserAcmsRela> list = dao.findByProperty(UserAcmsRela.class, "acmsId",acmsId, 1,"state","'V'");
    
    	return list.size()==0?null:list;
    }
	

 

      


}
