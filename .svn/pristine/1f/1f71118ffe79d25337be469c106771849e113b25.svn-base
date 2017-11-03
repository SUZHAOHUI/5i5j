
package com.oio.wawj.service;

import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.DeptInfo;
import com.oio.wawj.bean.RoleFunctions;
import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserRole;
import com.oio.wawj.util.PageListData;



public interface DepartmentService{ 

	public void saveDeptInfo(DeptInfo di);
	//部门信息
	public void saveDeptInfo(String batchNum,String transferId,String transferDateTime,String pushDataType,String setId
			,String deptId,String effdt,String effStatus,String descr,String cDeptType,String cDeptStype,String company ,String managerId,
			String managerPosn,String cDeptIdType,String descr4);
	//部门树信息
	public void saveDeptTree(String batchNum,String transferId,String transferDateTime,String pushDataType
			,String treeName,String effdt,String setId,String treeNode,String parentNodeName,String treeLevelNum);
	
	//部门级联关系
	public void saveDeptRela(String batchNum,String transferId,String transferDateTime,String pushDataType
			,String setId,String cValGather,String cFatherVal,String cFatherDescr,String cSonVal,String cSonDescr,
			String cDeptidType,String descr4);
	public void saveErp(String emplId,String mobile,String dn,String un);
	public void flush();
}
