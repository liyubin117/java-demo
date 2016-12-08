package com.useful;

class Person implements Cloneable{
	private String name;
	public Person(String name){
		this.name=name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String toString(){
		return "Person:"+this.name;
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		//return super.clone();
		return new Person(this.name);
	}
}

public class CloneableDemo1
{
	public static void main(String[] args) throws CloneNotSupportedException{
		Person p1=new Person("张三");
		Person p2=(Person)p1.clone();
		p2.setName("李四");
		System.out.println(p1);
		System.out.println(p2);

	}
};