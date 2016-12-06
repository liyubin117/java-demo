package com.collectionmap;

import java.util.Set;
import java.util.TreeSet;

class Person implements Comparable{
	private String name;
	private int age;
	
	public Person(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	@Override
	public String toString(){
		return this.name+":"+this.age;
	}

	@Override
	public int compareTo(Object o) {
		Person p=(Person)o;
		if(this.age<p.age){
			return -1;
		}else if(this.age>p.age){
			return 1;
		}else{
			return this.name.compareTo(p.name);
		}
	}
}

public class TreeSetDemo1 {

	public static void main(String[] args) {
		Set<Person> sp=new TreeSet<Person>();
		sp.add(new Person("张三",20));
		sp.add(new Person("李四",24));
		sp.add(new Person("王五",24));
		sp.add(new Person("王五",24));
		sp.add(new Person("王五",24));
		sp.add(new Person("赵六",25));
		sp.add(new Person("孙七",25));
		System.out.println(sp);
		
	}
}
