package org.rick;

import java.sql.*;

public class TestJDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/test2",
				"root",
				"mysql");
		
		Statement stat=conn.createStatement();
		ResultSet rs=null;
		DatabaseMetaData dbma=conn.getMetaData();


		try{
			//查询
			rs=stat.executeQuery("select id,name,count(1) as cnt from t1 group by id,name");
			ResultSetMetaData rsmd=rs.getMetaData();
			while(rs.next()){
				for(int i=0;i<rsmd.getColumnCount();i++){
					System.out.print(rs.getObject(i+1)+"\t");
				}
				System.out.println();
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
			
			String[] types={"TABLE"};
			rs=dbma.getTables(null, null, "t2", types);
			if(rs.next()){
			//删表
			row=stat.executeUpdate("drop table t2");
			System.out.println("drop successfully!");
			}
			
			//建表
			row=stat.executeUpdate("create table t2 (id int,name varchar(20),addr varchar(50))");
			System.out.println("create successfully!");
		
		}finally{
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

}
