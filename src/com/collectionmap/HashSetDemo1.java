package com.collectionmap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
//HashSet只实现Comparable接口仍是不足以去重的，还需重写equals、hashCode方法
class Person2 /*implements Comparable*/{
	private String name;
	private int age;
	
	public Person2(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	@Override
	public String toString(){
		return this.name+":"+this.age;
	}

	/*@Override
	public int compareTo(Object o) {
		Person2 p=(Person2)o;
		if(this.age<p.age){
			return -1;
		}else if(this.age>p.age){
			return 1;
		}else{
			return this.name.compareTo(p.name);
		}
	}*/
	
	@Override
	public boolean equals(Object o){
		if(this==(Person2)o){
			return true;
		}
		if((o instanceof Person2)){
			Person2 p=(Person2)o;
			if(this.name.equals(p.name)&&this.age==p.age){
				return true;
			}else{
				return false;
			}
		}
		return false;

		
	}
	
	@Override
	public int hashCode(){
		return this.name.hashCode()*this.age;
	}
}

public class HashSetDemo1 {

	public static void main(String[] args) {
		Set<Person2> sp=new HashSet<Person2>();
		sp.add(new Person2("张三",20));
		sp.add(new Person2("李四",24));
		sp.add(new Person2("李四",25));
		sp.add(new Person2("王五",24));
		sp.add(new Person2("王五",24));
		sp.add(new Person2("王五",24));
		sp.add(new Person2("赵六",25));
		sp.add(new Person2("孙七",25));
		System.out.println(sp);
		
		Set<String> ss=new HashSet<>();
		ss.add("c");
		ss.addAll(Arrays.asList("a","a","b","c","d","b"));
		System.out.println(Arrays.toString(ss.toArray()));
	}
}
