package com.oio.wawj.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper {
	public static final String url = "jdbc:mysql://120.24.214.65:3306/ucrm?useUnicode=true&characterEncoding=utf-8";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "dev";  
    public static final String password = "9o0p(O)P";  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
    public   DatabaseMetaData meta=null;
    
    public DBHelper(String sql) {  
        try {  
            Class.forName(name);//ָ����������  
            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
            pst = conn.prepareStatement(sql);//׼��ִ�����  
            meta = conn.getMetaData();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
