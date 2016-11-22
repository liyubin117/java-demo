package com.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DDL {
	private Connection conn;
	private Statement stat;
	private String object;
	private String columns;
	private String[] types;
	private ResultSet rs;
	private int rows;
	
	public DDL(Connection conn,String object,String columns,String types) throws SQLException{
		this.conn=conn;
		this.stat=conn.createStatement();
		this.object=object;
		this.columns=columns;
		this.types=types.split(",");
	}
	
	public static boolean isExists(Connection conn,String object,String[] types) throws SQLException{
		DatabaseMetaData dbma=conn.getMetaData();
		boolean flag=false;
		ResultSet rs=dbma.getTables(null, null, object, types);
		if(rs.next()){
			return true;
		}
		return flag;
	}
	
	public void create() throws SQLException{
		if(DDL.isExists(this.conn, this.object, this.types)){
			System.out.println(this.object+" Exists!");
		}else{
			for(String s:types){
				rows=stat.executeUpdate("create "+s+" "+object+" ("+columns+")");
				System.out.println("create "+this.object+" successfully!");
			}
		}
	}
	
	public void drop() throws SQLException{
		if(!DDL.isExists(this.conn, this.object, this.types)){
			System.out.println(this.object+"not Exists,Drop Failed!");
		}else{
			for(String s:types){
				rows=stat.executeUpdate("drop "+s+" "+object);
				System.out.println("drop "+this.object+" successfully!");
			}
		}
	}
}
