
package com.oio.wawj.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.oio.wawj.bean.DeptInfo;
import com.oio.wawj.bean.DeptRela;
import com.oio.wawj.bean.DeptTree;
import com.oio.wawj.bean.Erp;
import com.oio.wawj.bean.RoleFunctions;
import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserRole;
import com.oio.wawj.dao.DepartmentDAO;
import com.oio.wawj.dao.UserDAO;
import com.oio.wawj.service.DepartmentService;
import com.oio.wawj.service.UserService;
import com.oio.wawj.util.PageListData;




/**
 * @author 
 */
@Entity
public class DepartmentServiceImpl implements DepartmentService {
	@ManyToOne
	private DepartmentDAO dao;
	


	public DepartmentDAO getDao() {
		return dao;
	}



	public void setDao(DepartmentDAO dao) {
		this.dao = dao;
	}

	
	public void saveDeptInfo(String batchNum,String transferId,String transferDateTime,String pushDataType,String setId
			,String deptId,String effdt,String effStatus,String descr,String cDeptType,String cDeptStype,String company ,String managerId,
			String managerPosn,String cDeptIdType,String descr4){
		     DeptInfo di=new DeptInfo();
			    di.setBatchNum(batchNum);
			    di.setTransferId(transferId);
			    di.setTransferDateTime(transferDateTime);
			    di.setPushDataType(pushDataType);
			    di.setSetId(setId);
			    di.setDeptId(deptId);
			    di.setEffdt(effdt);
			    di.setEffStatus(effStatus);
			    di.setDescr(descr);
			    di.setCDeptType(cDeptType);
			    di.setCDeptStype(cDeptStype);
			    di.setCompany(company);
			    di.setManagerId(managerId);
			    di.setManagerPosn(managerPosn);
			    di.setCDeptidType(cDeptIdType);
			    di.setDescr4(descr4);
			    dao.save(di);	
	}
	

	public void saveDeptTree(String batchNum,String transferId,String transferDateTime,String pushDataType
			,String treeName,String effdt,String setId,String treeNode,String parentNodeName,String treeLevelNum){
		     DeptTree dt=new DeptTree();
			    dt.setBatchNum(batchNum);
			    dt.setTransferId(transferId);
			    dt.setTransferDateTime(transferDateTime);
			    dt.setPushDataType(pushDataType);
			    dt.setTreeName(treeName);
			    dt.setEffdt(effdt);
			    dt.setSetId(setId);
			    dt.setTreeNode(treeNode);
			    dt.setParentNodeName(parentNodeName);
			    dt.setTreeLevelNum(treeLevelNum);
			   
			    dao.save(dt);	
	}
	/*
	 *     "":"12" ,      
   "":"5I5J_BUSINESS_TYPE",  
    "":"121",     
    "":"11",
    "":"10",     
    "":"23",   
    "":"20", 
    "":"5i5j",  
	 */
	public void saveDeptRela(String batchNum,String transferId,String transferDateTime,String pushDataType
			,String setId,String cValGather,String cFatherVal,String cFatherDescr,String cSonVal,String cSonDescr,
			String cDeptidType,String descr4){
		     DeptRela dr=new DeptRela();
			     dr.setBatchNum(batchNum);
			     dr.setTransferId(transferId);
			     dr.setTransferDateTime(transferDateTime);
			     dr.setPushDataType(pushDataType);
			     dr.setSetId(setId);
			     dr.setCValGather(cValGather);
			     dr.setCFatherVal(cFatherVal);
			     dr.setCFatherDescr(cFatherDescr);
			     dr.setCSonVal(cSonVal);
			     dr.setCSonDescr(cSonDescr);
			     dr.setCDeptidType(cDeptidType);
			     dr.setDescr4(descr4);
			   
			    dao.save(dr);	
	}
	public void saveErp(String emplId,String mobile,String dn,String un){
		     Erp erp=new Erp();
		     erp.setEmplId(emplId);
		     erp.setUName(un);
		     erp.setDName(dn);
		     erp.setCMobile(mobile); 
			    dao.save(erp );	
	}
	/**
	 * 
	 * 保存部门信息
	 */
	
	
	public void saveDeptInfo(DeptInfo di) {
		dao.save(di);
	}
    
   public void flush(){
	   dao.flush();
   }
}
