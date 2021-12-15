package org.rick;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMyJDBCAPI {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://127.0.0.1:3306/test2";
	private static String user="root";
	private static String passwd="mysql";

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		
		Connection conn=DriverManager.getConnection(url, user, passwd);
		
		//better
		conn= DBCPConnectionPool.getConnectionPool(url, user, passwd, driver);

		DML insert=null;
		DML update=null;
		DDL ddl=null;
		Select select=null;
		String types="TABLE";

		try{
			ddl=new DDL(conn,"t1",null,types);
			ddl.drop();
			
			ddl=new DDL(conn,"t1","id int,name varchar(20)",types);
			ddl.create();
			
			insert=new DML(conn,"t1","id,name","8,\"li8\"");
			insert.insert();
			
			DML delete=new DML(conn,"t1",null,"where id=5");
			delete.delete();
			
			update=new DML(conn,"t1",null,"set name=\"hi\" where id=8");
			update.update();
			
			select=new Select(conn,"t1","id,name","");
			select.execute();
			
			
			insert=new DML(conn,"t2","id,name,addr"," 1,\"liyubin\",\"hangzhou\" ");
			insert.insert();
			
			select=new Select(conn,"t2","*","");
			select.execute();
		}finally{
			if(conn!=null){
				conn.close();
			}
		}		

	}
}
