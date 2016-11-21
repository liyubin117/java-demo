package com.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DML {
	private Statement stat;
	private String table;
	private String columns;
	private String values;
	private int rows;
	
	
	public DML(Connection conn,String table,String columns,String values) throws SQLException{
		this.stat=conn.createStatement();
		this.table=table;
		this.columns=columns;
		this.values=values;
	}
	
	public int insert() throws SQLException{
		try{
			rows=stat.executeUpdate("insert into "+this.table+" ("+this.columns+") "+"values ("+this.values+")");
			System.out.println("insert "+rows+" rows!");
			return rows;
		}finally{
			if(this.stat!=null){
				stat.close();
			}
		}
	}
	public int delete() throws SQLException{
		try{
			rows=stat.executeUpdate("delete from "+this.table+" "+this.values);
			System.out.println("delete "+rows+" rows!");
			return rows;
		}finally{
			if(this.stat!=null){
				stat.close();
			}
		}

	}
	public int update() throws SQLException{
		try{
			rows=stat.executeUpdate("update "+this.table+" "+this.values);
			System.out.println("update "+rows+" rows!");
			return rows;

		}finally{
			if(this.stat!=null){
				stat.close();
			}
		}
	}
	
}
