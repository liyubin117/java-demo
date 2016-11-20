package com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/test2",
				"root",
				"mysql");
		
		Statement stat=conn.createStatement();

		//查询
		ResultSet rs=stat.executeQuery("select id,name,count(1) as cnt from t1 group by id,name");
		while(rs.next()){
			System.out.println(rs.getInt(1)+"\t"+rs.getString("name")+"\t"+rs.getString("cnt"));
		}
		
		//插入数据
		int row=stat.executeUpdate("insert into t1(id,name) values(3,\'li\')");
		conn.setAutoCommit(false);
		conn.commit();
		System.out.println("insert "+row+" rows!");
		rs=stat.executeQuery("select * from t1 where id=3");
		while(rs.next()){
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
		}
		
		//删除数据
		row=stat.executeUpdate("delete from t1 where id=3");
		conn.setAutoCommit(false);
		conn.commit();
		System.out.println("delete "+row+" rows!");
		rs=stat.executeQuery("select * from t1 where id=3");
		while(rs.next()){
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
		}
		
		//更新数据
		row=stat.executeUpdate("update t1 set id=5 where id=2");
		conn.setAutoCommit(false);
		conn.commit();
		System.out.println("update "+row+" rows!");
		rs=stat.executeQuery("select * from t1 where id=5");
		while(rs.next()){
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
		}
		
		if(rs!=null){
			rs.close();
		}
		if(stat!=null){
			stat.close();
		}
		if(conn!=null){
			conn.close();
		}
	}

}
