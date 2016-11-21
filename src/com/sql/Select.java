package com.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {
	private Statement stat;
	private String table;
	private String columns;
	private String where;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private int columnCount;
	
	
	public Select(Connection conn,String table,String columns,String where) throws SQLException{
		this.stat=conn.createStatement();
		this.table=table;
		this.columns=columns;
		this.where=where;
	}
	
	public void execute() throws SQLException{
		rs=stat.executeQuery("select "+this.columns+" from "+this.table+" "+this.where);
		rsmd=rs.getMetaData();
		columnCount=rsmd.getColumnCount();
		if(getCount()==0){
			System.out.println("No Data!");
		}else{
			while(rs.next()){
				for(int i=1;i<=columnCount;i++){
					System.out.print(rs.getObject(i)+"\t");
				}
				System.out.println();
			}
		}
		if(rs!=null){
			rs.close();
		}
		if(stat!=null){
			stat.close();
		}
	}
	
	private int getCount() throws SQLException{
		int count=0;
		while(this.rs.next()){
			count++;
		}
		rs.beforeFirst();
		return count;
	}
}
