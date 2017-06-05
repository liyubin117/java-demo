package com.serial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class WritePerson {
	
	public static void main(String[] args) throws Exception{
		//Serialize
		ObjectOutputStream oos=null;
	
		try{
			oos=new ObjectOutputStream(new FileOutputStream("file"+File.separator+"person.txt"));
			Person p=new Person("liyubin",25);
			//oos.writeObject(p);
			p.writeObject(oos);
			oos.writeObject(p);
		}finally{
			if(oos != null){
				oos.close();
			}
		}
		


	}
	
}

