
package com.oio.wawj.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.oio.wawj.bean.Acms;
import com.oio.wawj.bean.Org;
import com.oio.wawj.bean.User;
import com.oio.wawj.dao.OrgDAO;
import com.oio.wawj.service.OrgService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.oio.wawj.util.PageListData;
import com.oio.wawj.util.TreeNode;
import com.oio.wawj.util.Utils;



/**
 * 主要功能：Org相关的service接口，主要用于处理业务逻辑，包括对dao层的操作方法
 * @author 
 * @lastmodify 
 */
public class OrgServiceImpl implements OrgService {
	private OrgDAO dao;
	private final static int CELL_TYPE_SHORT = -1;
	/**
	 * 锟斤拷锟斤拷锟铰�
	 * @param org 要锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	public void save(Org sysOrg) {
		dao.save(sysOrg);
	}
	
	/**
	 * 删锟斤拷锟铰�
	 * @param org 要删锟斤拷锟斤拷锟斤拷
	 */
	public void delete(Org sysOrg) {
		dao.delete(sysOrg);
	}

	public void deleteById(Integer id) {
		dao.deleteById(Org.class, id);
	}

	public void update(Org sysOrg) {
		dao.saveOrUpdate(sysOrg);
	}
	
	
	@SuppressWarnings("rawtypes")
	public PageListData findList( Map param, int pageNum, int pageSize) {
		String hql = "from Org o where 1=1  ";
		//sql锟斤拷锟斤拷值
		for (Object o : param.keySet()) {
				hql += " and o." + o.toString() + " ? ";
		}
		
		Object params[] = param.values().toArray();
		return dao.findList(Org.class, hql, params, pageNum, pageSize);
	}
	
	/**
	 * 锟叫讹拷锟角凤拷锟斤拷锟斤拷锟酵拷锟斤拷锟�
	 * @param param 锟斤拷锟斤拷锟斤拷筒锟斤拷锟街碉拷锟斤拷锟�
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public boolean isExistSameProperty(Map param) {
		return true;
	}
	
	/**
	 * 锟斤拷荽锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询Org锟叫憋拷
	 * @param id 
	 * @return Org锟叫憋拷锟斤拷锟较�
	 */
	public Org findById(String id) {
		return dao.findById(id);
	}
	
	/**
	 * 锟斤拷询锟斤拷锟斤拷Org锟叫憋拷
	 * @return Org锟叫憋拷锟斤拷息
	 */
	public List<Org> findAllBySetId() {
		return dao.findAllBySetId();
	}
	public List<Org> findAll(Long orgId) {
		return dao.findAll(orgId);
	}
	public List<Org> findAll() {
		return dao.findAll();
	}
	public TreeNode getreeNode(int cid){
		return dao.getreeNode(cid);
	}
	public List queryTreeNode(int cid){
		return dao.queryTreeNode(cid);
		
	}
	/**
	 * 锟叫讹拷锟角凤拷锟斤拷锟斤拷锟酵拷锟斤拷锟�
	 * @param name 参数名称
	 * @param type  
	 * @return boolean
	 */
	public boolean isExistName(String name, String type) {
		boolean flag=false;
		if ("add".equals(type)) {
			List<Org> list = dao.findByProperty(Org.class, "name", name, 1);
			flag=(list != null && list.size() > 0 ? true : false);
		} else if ("update".equals(type)) {
			String temp[]=name.split(":");
			List<Org> list = dao.findByProperty(Org.class, "name", temp[0], 1);
			if(Boolean.parseBoolean(temp[1]))
			    flag=(list!=null&&list.size()>1?true:false);
			else
				flag=(list!=null&&list.size()>0?true:false);
		}
		return flag;
	}
	
	/**
	 * 锟斤拷荽锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询Org锟叫憋拷
	 * @param param 锟斤拷锟斤拷锟斤拷筒锟斤拷锟街碉拷锟斤拷锟�
	 * @param type 锟斤拷锟斤拷锟斤拷
	 * @return Org锟叫憋拷锟斤拷息
	 */
	@SuppressWarnings("rawtypes")
	public List<Org> findByCondition(Map param, int type) {
		return dao.findByProperty(Org.class, null, null, type);
	}
	
	/**
	 * get锟斤拷锟斤拷
	 * @return OrgDAO
	 */
	public OrgDAO getDao() {
		return dao;
	}
	
	/**
	 * set锟斤拷锟斤拷
	 */
	public void setDao(OrgDAO dao) {
		this.dao = dao;
	}
	
	/**
	 * 锟斤拷荽锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询Org锟斤拷息
	 * @param id 锟斤拷锟斤拷锟斤拷
	 * @return Org锟斤拷息
	 */
	public Object findObjectById(String id) {
		return dao.getObjectById(id);
	}
	
	/**
	 * 批量导入虚拟号码信息，写入数据库
	 * @return 成功/失败（0表示成功，其他表示出错）
	 */
	 @SuppressWarnings({ "rawtypes" })
	public void importExcel(User user, String path, String excelFilename,File  efile) {
		//errorCode = 0;
		List<Object[]> list = parseExcelFile(path, excelFilename, efile);
		   
		if (list != null) 
		{
			String rootName = (String) list.get(0)[0];
			Org o = new Org(); 
			o.setOrgName(rootName);
			o.setStatus("V");
			o.setStatusDate(DateTime.getCurrentDateTime());
			save(o);		
			for(int i=0;i<list.size();i++)
			{	
				Object[] oList = list.get(i); 
				
				    for(int j=1;j<5;j++){
				    	  
					      Org o1 = new Org(); 
						  String parentName = (String)oList[j-1];
						  Org pOrg = dao.findByProperty(Org.class, "orgName",parentName).get(0);
						  Integer parentOrgId = pOrg.getId().getOrgId();
						  //System.out.println("parentOrgId-------"+parentOrgId);
						  String childName=(String) oList[j];
						 
						  List<Org> cOrg = dao.findByProperty(Org.class, "orgName",childName);   			 
//						  System.out.println(childName);
						  if(cOrg.size()==0){
							  o1.setOrgName(childName);
							  o1.setParentOrgId((long)parentOrgId);
							  o1.setStatus("V");
							  o1.setStatusDate(DateTime.getCurrentDateTime());
							  save(o1);
						  }
				    }

			}
		}
	}

	/**
	 * 解析excel文件 
	 * @param path 参数名 
	 * @param filename 参数名 
	 * @param excelFilename 参数名
	 * @return acms列表信息
	 */
	public List<Object[]> parseExcelFile(String path, String excelFilename,File efile)
	{

	   Workbook book;
	   List<Object[]> orgList = null;
	   File filenames = new File(path,excelFilename);
	   efile.renameTo(filenames);
	
		try 
		{
			FileInputStream fileInputStream = new FileInputStream(filenames);
			book = createWorkBook(fileInputStream, excelFilename);
			 if(book != null)
			 {
				//通过索引获取sheet信息  
				 Sheet sheet = book.getSheetAt(book.getActiveSheetIndex());  
				//遍历信息  
				int rowCount = sheet.getLastRowNum();
				/* 判断最后一行是否为空行 */
				while(true) {
					Row last_row = sheet.getRow(rowCount); 
					if(null != last_row ){
						if (null != last_row.getCell(1) && !"".equals(last_row.getCell(1).toString().trim()) ) {
							break;
						} else {
							rowCount --;
						}
					}else{
						rowCount --;
					}
				}
				if(rowCount == 0)
				{//若文件中没有数据,则提示"文件中无数据"
					
				}
				else if(rowCount > 0 && rowCount<=10000)
				{
					//用于存储用户信息  
					orgList = new ArrayList<Object[]>();
//					Set<String> excelNameSet = new HashSet<String>(); 
					ArrayList<String> aList = new ArrayList<String>();
					int countNum = rowCount;
					for (int i = 1; i <= countNum; i++)
					{ 
						
						int count = 0;
					    /* 去除文件中的空行 */
					    Row ros = sheet.getRow(i);  
					    if(ros != null){
					    	if ( null != ros.getCell(1) && !"".equals(ros.getCell(1).toString().trim()) ) {
					    	
					    		ros.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					    		String nameD1 = Utils.getCellValue(ros.getCell(0));
					    		String nameD2 = Utils.getCellValue(ros.getCell(1));
					    		String nameD3 = Utils.getCellValue(ros.getCell(2));
					    		String nameD4 = Utils.getCellValue(ros.getCell(3));
					    		String nameD5 = Utils.getCellValue(ros.getCell(4));
					    				//判断name是否有空值					    				
					    			Object[] obj=new Object[]{nameD1,nameD2,nameD3,nameD4,nameD5};						   				    				
					    			
					    		    orgList.add(obj); 
					    			count++;	
					    		}
							    
		                     } 	
					             
					}
				}
			 }
		}catch (Exception e){  
			e.printStackTrace();  
	
			orgList = null;
		}
		File target = new File(path, excelFilename);  
	    //如果文件已经存在，则删除原有文件  
	    if(target.exists())
	    	target.delete();  
		
		return orgList; 
	}
	
	private Object setCellIgorneNull(final Row r, int t, int i) {
		Object o = null;
		
		if (r.getCell(i) != null)
			if (t == CELL_TYPE_SHORT)
			{
				r.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
   	 			String s = r.getCell(i).getStringCellValue();
				try {
					o = Integer.valueOf(s);
				} catch (NumberFormatException e) {
					throw e;
				}
			}
			else
			{
				r.getCell(i).setCellType(t);
				if(t == Cell.CELL_TYPE_STRING)
	   	 			o = r.getCell(i).getStringCellValue();
				else if(t == Cell.CELL_TYPE_NUMERIC)
					o = r.getCell(i).getNumericCellValue();
				else if(t == Cell.CELL_TYPE_FORMULA)
					o = r.getCell(i).getCellFormula();
				else if(t == Cell.CELL_TYPE_ERROR)
					o = r.getCell(i).getErrorCellValue();
				else if(t == Cell.CELL_TYPE_BOOLEAN)
					o = r.getCell(i).getBooleanCellValue();
			}
				
		return o;
	}

	/**
	 * 判断文件类型 
	 * @param is 参数名 
	 * @param filename 参数名
	 * @return 文件Workbook对象
	 */
	public Workbook createWorkBook(InputStream is,String filename) throws IOException{ 
	
        if(filename.toLowerCase().endsWith("xls")){    
            return (Workbook) new HSSFWorkbook(is);    
        }else if(filename.toLowerCase().endsWith("xlsx")){  
            return new XSSFWorkbook(is);  
        }else{  
        	//errorCode = -1;	//文件类型不符合，页面提示"请选择excel格式文件!"
            return null;  
        }  
	}
	
	
	/**
	 * 锟斤拷询锟斤拷锟节碉拷锟斤拷息
	 * @return org锟叫憋拷锟斤拷息
	 */
	public List<Org> findParentNodeList(){
		return dao.findParentNodeList();
	}
	
	/**
	 * 锟斤拷询锟接节碉拷锟斤拷息
	 * @return org锟叫憋拷锟斤拷息
	 */
	public List<Org> findOrgChildNodesById(Integer orgId,String setId){
		return dao.getOrgChildNodesById(orgId,setId);
	}
    
	public Integer getStaffNumberByOrgId(Integer orgId,String setId,Long pOrgId){
		return dao.getStaffNumberByOrgId(orgId,setId,pOrgId);
	}

	
//	/**
//	 * 锟斤拷询锟接节碉拷锟斤拷息
//	 * @return org锟叫憋拷锟斤拷息
//	 */
//	public List<Staff> findOrgStaffById(Long orgId){
//		return dao.getOrgStaffById(orgId);
//	}
	
	/**
	 * 锟斤拷询锟斤拷org
	 * @return org锟叫憋拷锟斤拷息
	 */
//	public List<Org> findVaildOrg(){
//		return dao.getVaildOrg();
//	}
	
}

	
