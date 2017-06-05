package com.serial;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID=123L;

	String name;
	transient int age;
	int add;
	public Person(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	public void writeObject(java.io.ObjectOutputStream out) throws Exception{
		out.writeObject(new StringBuffer(this.name).reverse());
		out.writeInt(this.age);
	}
	public void readObject(java.io.ObjectInputStream in) throws Exception{
		this.name=((StringBuffer)in.readObject()).reverse().toString();
		this.age=in.readInt();
	}
	
	public String toString(){
		return "Person:"+this.name+" age:"+this.age+" add:"+this.add;
	}
}
