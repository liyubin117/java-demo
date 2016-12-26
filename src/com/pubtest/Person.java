package com.pubtest;

public class Person implements Cloneable{
	private String name;
	private String addr;
	private int age;
	
	public Person(String name){
		this.name=name;
	}
	public Person(){
		
	}
	public Person(String name,String addr,int age){
		this.name=name;
		this.addr=addr;
		this.age=age;
	}
	public String toString(){
		return "Person name:"+this.name+" addr:"+this.addr+" age："+this.age;
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		//return super.clone();
		return new Person(this.name,this.addr,this.age);
	}
}