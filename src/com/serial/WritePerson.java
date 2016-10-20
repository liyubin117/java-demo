package com.serial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WritePerson {
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		//Serialize
		ObjectOutputStream oos=null;
	
		try{
			oos=new ObjectOutputStream(new FileOutputStream("D:/eclipse_luna/workspace_luna/JavaTest/file/person.txt"));
			Person p=new Person("liyubin",25);
			oos.writeObject(p);
		}finally{
			if(oos != null){
				oos.close();
			}
		}
		


	}
	
}

