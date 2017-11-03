
package com.oio.wawj.dao;

import java.util.List;
import java.util.Map;

import com.oio.wawj.bean.Function;
import com.oio.wawj.util.PageListData;




/**
 * 权限列表数据库操作接口
 * @version 1.0
 *
 */
public interface FunctionDAO extends BaseDAO<Function, Short>  {

	/**
	 * 通过客户ID及模块ID获取子菜单列表
	* <p>Title: findLeftFunctionNameById </p>
	* <p>custId: 客户ID</p>
	* <p>functionId: 模块ID</p>
	* return 子菜单列表
	*/
	List<Function> findLeftFunctionNameById(Short userId, Short functionId);
	public List getSubListById(final String id);
	/**
	 * 通过客户类别获取权限列表
	* <p>Title: findList </p>
	* <p>category: 类别</p>
	* return 权限列表
	*/
	List<Function> findList(Integer category);

	/**
	 * 通过模块ID类别获取模块信息
	* <p>Title: getFunctionById </p>
	* <p>functionId: 模块ID</p>
	* return 模块信息
	*/
	public Function getFunctionById(Short functionId);
	public List<Function> getNoUseFunctionById( final List<Short> functionIds);
	public List<Function> findTopFunctionNameById(Short id);
	public String getParentList();
	public  List<Function> findAllFunctionList();
	/**
	 * 通过模块ID获取模块名称
	* <p>Title: getNameByIdList </p>
	* <p>functionIds: 模块ID列表</p>
	* return 模块名称列表
	*/
	List<String> getNameByIdList(List<Integer> functionIds);
	public PageListData getList(final String hql,final Map param,final int pageNum,final int pageSize);

}