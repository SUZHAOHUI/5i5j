package com.oio.wawj.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oio.wawj.bean.Acms;
import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.Org;
import com.oio.wawj.bean.ParameterOrgRela;
import com.oio.wawj.bean.SubsRela;
import com.oio.wawj.bean.User;
import com.oio.wawj.bean.UserAcmsRela;
import com.oio.wawj.dao.AxbDAO;
import com.oio.wawj.dao.NumberDAO;
import com.oio.wawj.dao.StaffDAO;
import com.oio.wawj.dao.UserAcmsRelaDAO;
import com.oio.wawj.dao.impl.BaseDAOImpl;
import com.oio.wawj.service.StaffService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.AxOrder;
import com.oio.wawj.util.DateTime;
import com.oio.wawj.util.OVLoadProperties;
import com.oio.wawj.util.PageListData;
import com.oio.wawj.util.SecurityHelper;
import com.oio.wawj.util.UUIDGenerator;
import com.oio.wawj.util.Utils;





@Entity
public class StaffServiceImpl implements StaffService {
	@ManyToOne
	private StaffDAO dao;
	private UserAcmsRelaDAO uarDao;
	private AxbDAO subdao;
	private NumberDAO numDao;
	public NumberDAO getNumDao() {
		return numDao;
	}

	public void setNumDao(NumberDAO numDao) {
		this.numDao = numDao;
	}

	public AxbDAO getSubdao() {
		return subdao;
	}

	public void setSubdao(AxbDAO subdao) {
		this.subdao = subdao;
	}

	public UserAcmsRelaDAO getUarDao() {
		return uarDao;
	}

	public void setUarDao(UserAcmsRelaDAO uarDao) {
		this.uarDao = uarDao;
	}

	public StaffDAO getDao() {
		return dao;
	}

	public void setDao(StaffDAO dao) {
		this.dao = dao;
	}

	/**
	 * 锟斤拷锟斤拷锟铰�
	 * @param User 要锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	public void save(User sysUser) {
		dao.save(sysUser);
	}
   


	

	/**
	 * 删锟斤拷锟铰�
	 * @param id 要删锟斤拷锟斤拷锟斤拷
	 */
	public void deleteById(Long id) {
		dao.deleteById(User.class, id);
	}


	
	/**
	 * 锟斤拷锟铰硷拷录
	 * @param simc 要锟斤拷锟铰碉拷锟斤拷锟�
	 */
	public void update(User sysUser) {
		dao.saveOrUpdate(sysUser);
	}
	

	
	@SuppressWarnings( "unchecked" )
	public PageListData findList(Map param, int pageNum, int pageSize) {
		return dao.findList(param, pageNum, pageSize);
	}
	@SuppressWarnings( "unchecked" )
	public PageListData findExportList(Map param, int pageNum, int pageSize) {
		return dao.findExportList(param, pageNum, pageSize);
	}

	/**
	 * 锟斤拷荽锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷询SysRole锟叫憋拷
	 * @param id 
	 * @return SysRole锟叫憋拷锟斤拷锟较�
	 */
	public User findById(Long id) {

	//	User sysUser = dao.findById(User.class, id);
		return dao.findById(User.class, id);
	}
	/**
	 * 锟叫讹拷锟角凤拷锟斤拷锟斤拷锟酵拷锟斤拷锟�
	 * @param name 锟斤拷锟斤拷锟斤拷
	 * @param type 锟斤拷锟斤拷锟斤拷
	 * @return boolean
	 */
	public boolean isExistName(String name,String statusName, String statusValue) {
		boolean flag = false;
	        
			List<User> list = dao.findByProperty(User.class, "code",
					name, 1,statusName, statusValue);
			flag = (list != null && list.size() > 0 ? true : false);
		
		return flag;
	}
	public boolean isExist(String param,String statusName, String statusValue,Boolean flag) {
		List<User> list;
	       if(flag){
			 list = dao.findByProperty(User.class, "code",
					param, 1,statusName, statusValue);
	       }else{
	    	 list = dao.findByProperty(User.class, "email",
						param, 1,statusName, statusValue); 
	       }
			flag = (list != null && list.size() > 0 ? true : false);
		
		return flag;
	 }

	public JSONArray getChannelList(Long operator) {
		return dao.getChannelList(operator);
	}

	public String getDutyList(Long operator) {
		return dao.getDutyList(operator);
	}


    public Acms  getAcmsByacms(String acms){
    	
    	return dao.findByAcms(acms);
    }
    public User findUserByCode(String code){
    	List<User> list = dao.findByProperty(User.class, "code", code);
    	return  (list.size()==0)?null:list.get(0);
    }
    public User findUserByName(String name,String setId){
    	List<User> list = dao.findByProperty(User.class, "name", name,1,"setId",setId);
    	return  (list.size()==0)?null:list.get(0);
    }
    public List getUserAcmsRela(Long userId,String channel){
    	
    	return dao.getUserAcmsRela(userId,channel);
    }
    public Channel findChannelById(Integer channelId){
    	return dao.findChannelById(channelId);
    }
    public ParameterOrgRela findRingBySetId(String setId){
    	return dao.findRingBySetId(setId);
    }
    public Org findOrgById(Long orgId){
    	return dao.findOrgById(orgId);
    }
    public List<Object> findSubsRelaByUserId(String code){
    	return dao.findSubsRelaByUserId(code);
    }
    public List<Object> findSubsRelaByCodeAndAcms(String code,String acms){
    	return dao.findSubsRelaByCodeAndAcms(code,acms);
    }
    public List<Object> findUnSubsRelaByUserId(String code){
    	return dao.findUnSubsRelaByUserId(code);
    }
    public List<Object> findSubsRelaByXnum(String xnum){
    	return dao.findSubsRelaByXnum(xnum);
    }
    /**
	 * 批量导入虚拟号码信息，写入数据库
	 * @return 成功/失败（0表示成功，其他表示出错）
	 */
	public String importExcel( String path, String excelFilename,File  efile) {
		String errorCode = "";
		//errorCode = 0;
		List<Object[]> list = parseExcelFile(path, excelFilename, efile);
		//Object[] obj=new Object[]{code, ja.toArray(),calling};
		errorCode = dao.importExcel(list);
		return errorCode;
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
	   List<Object[]> staffList = null;
	   File filenames = new File(path,excelFilename);
	   efile.renameTo(filenames);
	   JSONArray channel=dao.getChannelList(AboutOperator.getOperatorId());
	
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
						if (null != last_row.getCell(0) && !"".equals(last_row.getCell(0).toString().trim()) ) {
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
					System.out.println("文件中无数据");
				}
				else if(rowCount > 0 && rowCount<=15000)
				{
					//用于存储用户信息  
					staffList = new ArrayList<Object[]>();
//					Set<String> excelNameSet = new HashSet<String>(); 
					ArrayList<String> aList = new ArrayList<String>();
					int countNum = rowCount;
					ArrayList ja= new ArrayList();
			        JSONArray channelAndAcms= new JSONArray();
				   // Object[] channelAndAcms =null; 
					for (int i = 1; i <= countNum; i++)
					{ 
						
						int count = 0;
					    /* 去除文件中的空行 */
					    Row ros = sheet.getRow(i);  
					    if(ros != null){
					    	if ( null != ros.getCell(0) && !"".equals(ros.getCell(0).toString().trim()) ) {
					    		ros.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					    		String code = Utils.getCellValue(ros.getCell(1));//用户
					    		String name = Utils.getCellValue(ros.getCell(2));//姓名
//					    		String dAuth = Utils.getCellValue(ros.getCell(4));//权限
//					    		String freeze = Utils.getCellValue(ros.getCell(5));//冻结
					    		String calling = Utils.getCellValue(ros.getCell(4));//主叫
					    		String channelName="";
					    		String acms="";	    		
					    		  for(int j=0;j<=(channel.size()-1)*4;j=j+4){
	                             
					    			  JSONObject jo=new JSONObject();	    			   
					    			  channelName = Utils.getCellValue(ros.getCell(j/2+5));
					    			  acms = Utils.getCellValue(ros.getCell(j/2+6));
					    			  jo.put("cname", channelName);
					    			  jo.put("acms", acms);
					    			  channelAndAcms.add(jo);  
					    			  
					        	  }	 					    		 
					    				//判断name是否有空值					    				
//					    		freeze = freeze == null?"":freeze;
//					    		freeze = freeze.equals("否")?"V":"I";
					 
					    		  Object[] obj=new Object[]{code, channelAndAcms.toString(),calling,name};						   				    				
					    		  if(i>=1){
					    			  channelAndAcms.clear();
					    		  }
					    		    staffList.add(obj); 
					    			count++;	
					    		}
							    
		                     } 					             
					}
					 
				}
			 }
		}catch (Exception e){  
			e.printStackTrace();  
	
			staffList = null;
		}
		File target = new File(path, excelFilename);  
	    //如果文件已经存在，则删除原有文件  
	    if(target.exists())
	    	target.delete();  
		
		return staffList; 
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
	
	@SuppressWarnings("rawtypes")
	private List getExportData(List list, List<Object> channelList, JSONArray channel, int num){
		List<String> numList = new ArrayList<String>();
		List<Object[]> dataList = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
			numList.clear();
			Object[] o = (Object[]) list.get(i);
			//获取不同号码
			for(int ii = 0; ii < channelList.size(); ii ++){
				Object[] channelInfo = (Object[]) channelList.get(ii);
				if(o[0].toString().equals(channelInfo[0].toString())){
					if(!numList.contains(channelInfo[2].toString())){
						numList.add(channelInfo[2].toString());
					}
				}
			}

			if(numList.size() > 0){
				for(int jj = 0; jj < numList.size(); jj ++){
					String anum = numList.get(jj);//获取号码
					List<Object[]> clist = new ArrayList<Object[]>();
					for(int ii = 0; ii < channelList.size(); ii ++){
						Object[] channelInfo = (Object[]) channelList.get(ii);
						if(anum.equals(channelInfo[2].toString())){
							boolean flg = false;
							for(int j = 0; j < clist.size(); j ++){
								Object[] ci = (Object[]) clist.get(j);
								if(ci[1].toString().equals(channelInfo[1].toString())){
									if(!ci[3].toString().contains(channelInfo[3].toString())){
										ci[3] = ci[3] + "," + channelInfo[3];
										clist.set(j, ci);
									}
									flg = true;
									break;
								}
							}
							if(!flg)
								clist.add(channelInfo);
						}
					}

					Object[] obj = new Object[6 + channel.size() * 2];
					obj[0] = i;
					obj[1] = o[2] == null ? "" : o[2].toString();
					obj[2] = o[1] == null ? "" : o[1].toString();
					obj[3] = o[3] == null ? "" : o[3].toString();
					obj[4] = anum;
					
					for (int j = 0; j < channel.size() * 2; j = j + 2) {
						JSONObject jo = (JSONObject) channel.get(j / 2);
						obj[j + 5] = jo.getString("cname");

						for(int ii = 0; ii < clist.size(); ii ++){
							Object[] channelInfo = (Object[]) clist.get(ii);
							if(channelInfo[4].toString().equals(jo.getString("cname"))){
								obj[j + 6] = channelInfo[3].toString();
								break;
							} 
						}
						
					}

					String orgName = o[5] == null ? "" : o[5].toString();
					if (!orgName.isEmpty()) {
						obj[num - 1] = orgName;
					} else {
						obj[num - 1] = "";
					}
					clist = null;
					//list.set(i, obj);
					dataList.add(obj);
				}
			}else{
				Object[] obj = new Object[6 + channel.size() * 2];
				obj[0] = i;
				obj[1] = o[2] == null ? "" : o[2].toString();
				obj[2] = o[1] == null ? "" : o[1].toString();
				obj[3] = o[3] == null ? "" : o[3].toString();
				obj[4] = o[9] == null ? "" : o[9].toString();
				
				for (int j = 0; j < channel.size() * 2; j = j + 2) {
					JSONObject jo = (JSONObject) channel.get(j / 2);
					obj[j + 5] = jo.getString("cname");
					obj[j + 6] = "";
				}
				
				String orgName = o[5] == null ? "" : o[5].toString();
				if (!orgName.isEmpty()) {
					obj[num - 1] = orgName;
				} else {
					obj[num - 1] = "";
				}
				//list.set(i, obj);
				dataList.add(obj);
			}
		}
		return dataList;
	}
	
	static Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);
	@SuppressWarnings({ "rawtypes" })
	public String exportExcel(List list, List<Object> channelList, String targetDirectory, String code, String setId) {
		String fileName = "";
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("虚拟号导入绑定");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		JSONArray channel = dao.getChannelList(AboutOperator.getOperatorId());
		// String[] title={"序号","用户","姓名","职务","权限","冻结","渠道","手机号码","小号","部门"};
		ArrayList<String> al = new ArrayList<String>();
		al.add("序号");
		al.add("用户");
		al.add("姓名");
		al.add("职务");
		al.add("手机号码");

		for (int j = 0; j < channel.size(); j++) {
			al.add("渠道");
			al.add("小号");
		}
		al.add("部门");
		for (int i = 0; i < al.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(al.get(i));
			cell.setCellStyle(style);
		}

		if (list != null) {
			list = getExportData(list, channelList, channel, al.size());
			
			try{
				for(int i = 0; i < list.size(); i++){
					Object[] obj = (Object[]) list.get(i);
					row = sheet.createRow(i + 1);
					for(int j = 0; j < obj.length; j++){
						row.createCell(j).setCellValue(obj[j] != null ? obj[j].toString():"");
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		// 增加 渠道ID提醒行
		row = sheet.createRow((int) list.size() + 4);

		HSSFCellStyle style1 = wb.createCellStyle();
		Font font = wb.createFont();
		font.setColor(HSSFColor.RED.index);
		style1.setFont(font);

		try {
			fileName = code + "_" + UUIDGenerator.getUUID() + ".xls";
			File file = new File(targetDirectory, fileName);
			if (!file.isDirectory()) {
				file.delete();
			}
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
			fileName = null;
		}
		return fileName;
	}

	@Override
	public List<Object> findChannelRela() {
		return dao.findChannelRela();
	}



}
