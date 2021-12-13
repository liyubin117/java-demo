package org.rick.sql;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPConnectionPool {
	private static BasicDataSource bds;
	private static Connection conn;
	
	
	public static Connection getConnectionPool(String url,String user,String passwd,String driver){
		bds=new BasicDataSource();
		bds.setDriverClassName(driver);
		bds.setUrl(url);
		bds.setUsername(user);
		bds.setPassword(passwd);
		bds.setInitialSize(5);
		bds.setMinIdle(2);
		
		try {
			conn=bds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	
}
