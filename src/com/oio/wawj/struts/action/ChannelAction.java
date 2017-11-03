package com.oio.wawj.struts.action;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import com.oio.wawj.bean.Channel;
import com.oio.wawj.bean.ChannelId;
import com.oio.wawj.bean.User;
import com.oio.wawj.service.ChannelService;
import com.oio.wawj.util.AboutOperator;
import com.oio.wawj.util.DateTime;
import com.oio.wawj.util.OVLoadProperties;
import com.oio.wawj.util.PageListData;
import com.oio.wawj.util.SecurityHelper;
import com.opensymphony.xwork2.ActionContext;


public class ChannelAction extends BaseAction {


	private Channel channel ;

	private ChannelService channelService;
	
	


	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	private Integer channelId;
	private String channelName;
	private String acmsCount;
	private String state;
	private Date stateDate;
	private String operator;
	private String channelList;
	private String result;

	/**鏌ヨ鍏ㄩ儴
	 * @return the channelList
	 */
/*	public String getChannelList() {
		channelList = channelService.findAll();
		setResult(channelList);
		return "success";
	}*/
	public String getChannelList() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String currentPage1=request.getParameter("currentPage");
		
		if(currentPage1 != null && !currentPage1.equals("") ){
			currentPage=Integer.valueOf(currentPage1);
		}
	
	   // pageListData = userService.findList(currentPage,pageSize);	
	    PageListData pageListData = channelService.findAllChannel(currentPage,pageSize);
	    
		if(pageListData.getTotalcount()!=0)
		{		
			JSONObject pageviewjson = pageListData.parseJSON(0,"channelId","channelName", "count");	
			  System.out.println(pageviewjson.toString());
			  setResult(pageviewjson.toString());
		}else{
				  
		setResult("[]");	
	    }

	return SUCCESS;
	}

	/**
	 * 娣诲姞Channel
	 * @return add
	 * @throws UnsupportedEncodingException 
	 */
	public String add() {
		
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String channelName=request.getParameter("channelName");
/*		try {
			channelName = new String(channelName.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
		Channel channel1 = new Channel();
	    ChannelId channel2 = new ChannelId();
		

        channel1.setState("V");
        channel1.setStateDate(DateTime.getCurrentDateTime());
 
        channel1.setOperator(String.valueOf(AboutOperator.getOperatorId()));


        
        

        channel2.setChannelName(channelName);
		channel1.setId(channel2);
		channelService.save(channel1);
		setOperationLogResult("result:'" + getText("operatelog.role.add.success") +"', reason:''");
		return "success";
		
		
		
		
		

	}
	
	/**
	 * 鍒犻櫎Channel
	 * @return successDelete
	 */
	public String delete() {
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		
		String Id=request.getParameter("Id");
		System.out.println("------------------:"+Id);
		ChannelId cId=new ChannelId();
		cId.setChannelId(Integer.valueOf(Id));
		
		Channel c = channelService.findById(cId);
		c.setState("I");
		c.setStateDate((Timestamp) DateTime.getCurrentDateTime());
		c.setOperator(String.valueOf(AboutOperator.getUser().getUserId()));
		channelService.update(c);
		//channelService.deleteById(cId);
		setOperationLogResult("result:'" + getText("operatelog.role.delete.success") +"', reason:''");
		return "success";
	}

	 /**
	 * @return the ChannelList
	 */
	 public String channelList() {
		long operator = AboutOperator.getOperatorId();
		String channelList = channelService.getChannelList(operator);
		setResult(channelList);
		return "success";
	}
	
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}



	public void setChannelList(String channelList) {
		this.channelList = channelList;
	}

	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public ChannelService getChannelService() {
		return channelService;
	}
	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}

	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getAcmsCount() {
		return acmsCount;
	}
	public void setAcmsCount(String acmsCount) {
		this.acmsCount = acmsCount;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getStateDate() {
		return stateDate;
	}
	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	
}
