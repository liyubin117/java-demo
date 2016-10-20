package com.serial;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadPerson {
	public static void main(String[] args) throws IOException{
		//Deserialize
		ObjectInputStream ois=null;
		
		try{
			ois=new ObjectInputStream(new FileInputStream("D:/eclipse_luna/workspace_luna/JavaTest/file/person.txt"));
			Person p=(Person)ois.readObject();
			System.out.println(p.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(ois != null){
				ois.close();
			}
		}
	}
}
