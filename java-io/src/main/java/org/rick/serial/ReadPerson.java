package org.rick.serial;

import model.PersonObjectWR;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadPerson {
	public static void main(String[] args) throws Exception{
		//Deserialize
		ObjectInputStream ois=null;
		
		try{
			ois=new ObjectInputStream(new FileInputStream("file"+File.separator+"person.txt"));
			//Person p=(Person)ois.readObject();
			PersonObjectWR p=new PersonObjectWR("a",1);
			//若使用自动序列化机制，由于age是transient修饰的，会被忽略，aeg为0；但调用自定义的方法后可以读写
			p.readObject(ois);
			System.out.println("自定义序列化："+p.toString());
			
			p=(PersonObjectWR)ois.readObject();
			System.out.println("自动序列化机制："+p.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(ois != null){
				ois.close();
			}
		}
	}
}
