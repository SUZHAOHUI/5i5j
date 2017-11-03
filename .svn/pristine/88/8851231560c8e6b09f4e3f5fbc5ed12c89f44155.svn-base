package com.oio.wawj.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
	public static final String url = "jdbc:mysql://101.200.221.216:3306/ucrm?useUnicode=true&characterEncoding=utf-8&" +
			"zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "dev";  
    public static final String password = "java.ucrm1234";  
  
    public Connection connection = null;  
    
    
    public DBConnection() {  
        try {  
            Class.forName(name);//ָ����������  
            connection = DriverManager.getConnection(url, user, password);//��ȡ����  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try {  
            this.connection.close();  
            
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
