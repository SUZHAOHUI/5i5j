package com.oio.wawj.service.impl;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.oio.wawj.bean.TransferNumberSet;
import com.oio.wawj.dao.TransferNumberDAO;
import com.oio.wawj.service.TransferNumberService;




/**
 * 锟斤拷要锟斤拷锟杰ｏ拷TransferNumberSet锟斤拷氐锟絪ervice锟接口ｏ拷锟斤拷要锟斤拷锟节达拷锟斤拷业锟斤拷锟竭硷拷锟斤拷锟斤拷锟斤拷锟斤拷dao锟斤拷牟锟斤拷锟斤拷锟斤拷锟�
 * @author 
 */
@Entity
public class TransferNumberServiceImpl implements TransferNumberService {
	@ManyToOne
	private TransferNumberDAO dao;
	
	public TransferNumberDAO getDao() {
		return dao;
	}

	public void setDao(TransferNumberDAO dao) {
		this.dao = dao;
	}

	/**
	 * 锟斤拷锟斤拷锟铰�
	 * @param TransferNumberSet 要锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	public void save(TransferNumberSet sysTransferNumberSet) {
		dao.save(sysTransferNumberSet);
	}
   


	

	/**
	 * 删锟斤拷锟铰�
	 * @param id 要删锟斤拷锟斤拷锟斤拷
	 */
	public void deleteById(Long id) {
		dao.deleteById(TransferNumberSet.class, id);
	}


	
	/**
	 * 锟斤拷锟铰硷拷录
	 * @param simc 要锟斤拷锟铰碉拷锟斤拷锟�
	 */
	public void update(TransferNumberSet sysTransferNumberSet) {
		dao.saveOrUpdate(sysTransferNumberSet);
	}
	

	


	/**
	 * 锟斤拷荽锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询SysRole锟叫憋拷
	 * @param id 
	 * @return SysRole锟叫憋拷锟斤拷锟较�
	 */
	public TransferNumberSet findById(Long id) {

	//	TransferNumberSet sysTransferNumberSet = dao.findById(TransferNumberSet.class, id);
		return dao.findById(TransferNumberSet.class, id);
	}



	



      


}
