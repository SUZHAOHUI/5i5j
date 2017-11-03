package com.oio.wawj.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NumberManage {
	//70数据库
		public static final String url = "jdbc:mysql://101.201.101.70:3306/icrm?useUnicode=true&characterEncoding=utf-8";
		
		//216数据库
		//public static final String url = "jdbc:mysql://101.200.221.216:3306/ucrm?useUnicode=true&characterEncoding=utf-8";
	    public static final String name = "com.mysql.jdbc.Driver";  
	    public static final String user = "dev";  
	    //70密码
	    public static final String password = "1q2w!Q@W"; 
	    //216密码
	   // public static final String password = "java.ucrm1234";  
	  
	    public static Connection conn = null;  
	    public static Statement pst = null;  
	    public static   DatabaseMetaData meta=null;
	     
	  
	    public void close() {  
	        try {  
	            this.conn.close();  
	            this.pst.close();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	    }  
		public static void  insertData(){
			String pathName = "C:\\data\\re.txt";
		    File filename = new File(pathName);
		    InputStreamReader reader = null;
			try {
				reader = new InputStreamReader(  
				        new FileInputStream(filename));
			    BufferedReader br = new BufferedReader(reader);
			    String line = "";
			    int count=1;
			    String sql="";
			    try {  
		            Class.forName(name);//ָ����������  
		            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
		            pst = conn.createStatement();//׼��ִ�����  
		            meta = conn.getMetaData();
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
			   
				 while ((line = br.readLine()) != null) {  
			           //一次读入一行数据 
			            String[] strArray = line.split(" ");
			            String acms=strArray[0];
			            String code=strArray[1];
					    //String acms= line.trim();
					    System.out.println(acms);
//			            System.out.println(count+":acms------"+acms+"-----code-------"+code);
			            //我爱我家号码导入
//			            sql=" insert into acms(acms,code,state,state_date,operator_id,status,status_date) values(" +"'"+acms
//			            		+"'"+","+"'"+code+"'"+","+"'U','2017-10-15 14:20:00','1','I','2017-10-15 14:20:00')";
			            //216  secret_storeroom 号码插入
//			            sql=" insert into secret_storeroom(number,code,createdate,roamarea,comments) values(" +"'"+acms
//			            		+"'"+","+"'"+code+"'"+","+"'2017-10-15 14:20:00','广西','east comm')";
			            //216
//			            sql=" insert into secret_info(number,code,appkey,createdate,city,belong_to,status,status_date" +
//			            		",comments) values(" +"'"+acms
//			            		+"'"+","+"'"+code+"'"+","+"'wywawj','2017-10-15 14:20:00','南京','1','V','2017-10-15 14:20:00','east comm')";
			            sql=" update user_acms_rela set state='I',state_date='2017-10-16 18:35:00'" +
			            		" WHERE acms_user_id in (select tj.auid from (SELECT acms_user_id as auid"+
                                " FROM acms as a, user_acms_rela as uar WHERE a.acms_id=uar.acms_id AND acms=" +"'"+acms+"'"+
			            		" LIMIT 1)tj)";
			          
			            pst.execute(sql);
			            count++;
			            
			        }
				 br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		    
			
		}
		public static void  subData(){
			String pathName = "F:\\test.txt";
		    File filename = new File(pathName);
		    InputStreamReader reader = null;
			try {
				reader = new InputStreamReader(  
				        new FileInputStream(filename));
			    BufferedReader br = new BufferedReader(reader);
			    String line = "";
			    int count=1;
			    String sql="";
			    try {  
		            Class.forName(name);//ָ����������  
		            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
		            pst = conn.createStatement();//׼��ִ�����  
		            meta = conn.getMetaData();
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
			   
				 while ((line = br.readLine()) != null) {  
			           //一次读入一行数据 
			           // String[] strArray = line.split(" ");
			            //String acms=strArray[0];
			            //String code=strArray[1];
					    //String acms= line.trim();
					  // System.out.println("count------"+count+":----------"+line);
                        
			            if(line !=null && !line.equals("")){
			            	String[] strArray = line.split(",");
			            	System.out.println(line+"-----line");
			            	for(int i=0;i<strArray.length;i++){
			            		System.out.println(strArray[i]);
			 
			            		sql=" update acms set status='I' where acms = "+strArray[i];
				                //pst.execute(sql);
			            	}
					        
			            }

			            System.out.println(count++);
			            
			        }
				 br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		    
			
		}

		public static void main(String[] args){
		
			subData();
			
			
		}
}
