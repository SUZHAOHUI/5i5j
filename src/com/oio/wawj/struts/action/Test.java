package com.oio.wawj.struts.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		
		//String [] s1 =new String [] { "setId":"12","jobCode":"2341","effDt":"2017/08/22","effStatus":"34","descr":"11","descrShort":"5i5j"};
		
		JSONObject resjo = new JSONObject();
		String ss= "[{ 'setId':'12','jobCode':'2341','descr':'11','descrShort':'5i5j'}]";

		resjo.put("batchLines", ss);
		
		
		JSONArray bl=resjo.getJSONArray("batchLines");
		
        //String [] bl = resjo.get("batchLines");
		String setId=null;
		String descr=null;
		String descrShort=null;
        for (int i = 0; i <bl.size(); i++) {
        	JSONObject js=  JSONObject.fromObject(bl.get(i));
        	 setId=js.getString("setId");
        	 descr=js.getString("descr");
        	 descrShort=js.getString("descrShort");
        	
        	System.out.println(setId);
        	System.out.println(descr);
        	System.out.println(descrShort);
		}
		
		
	}
}
