package com.useful;

import java.util.Arrays;

class Personc implements Comparable<Personc>{
	private String name;
	private int age;
	
	public Personc(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	@Override
	public String toString(){
		return "name:"+this.name+" age:"+this.age;
	}

	@Override
	public int compareTo(Personc o) {
		if(this.age>o.age){
			return 1;
		}else if(this.age<o.age){
			return -1;
		}else{
			return this.name.compareTo(o.name);
		}
	}	
}

public class ComparableDemo1 {
	public static void main(String[] args){
		Personc p1=new Personc("li",24);
		Personc p2=new Personc("ye",22);
		Personc p3=new Personc("liyubin",24);
		Personc[] ps={p1,p2,p3};
		Arrays.sort(ps);
		System.out.println(Arrays.toString(ps));
	}
}
