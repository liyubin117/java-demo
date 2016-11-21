package com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMyJDBCAPI {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/test2", 
				"root", 
				"mysql");
		
		DML insert=new DML(conn,"t1","id,name","8,\"li8\"");
		insert.insert();
		
//		DML delete=new DML(conn,"t1",null,"where id=8");
//		delete.delete();
		
		DML update=new DML(conn,"t1",null,"set name=\"hi\" where id=8");
		update.update();
		
		Select select=new Select(conn,"t1","id,name","");
		select.execute();
		
		
		insert=new DML(conn,"t2","id,name,addr"," 1,\"liyubin\",\"hangzhou\" ");
		insert.insert();
		
		select=new Select(conn,"t2","*","");
		select.execute();
		
		
		if(conn!=null){
			conn.close();
		}
	}
}
