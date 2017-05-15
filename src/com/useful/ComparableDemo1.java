package com.useful;

import java.util.Arrays;
import java.util.Comparator;

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

class ComparableString{
	private String t;
	
	public ComparableString(String s){
		this.t=s;
	}
	public int compareTo(Comparable<? extends String> o) {
		return t.compareTo((String) o);
	}
}

class ComparatorObject<T>{
	private Comparator<? super T> cpr;
	private T t;
	
	public ComparatorObject(T t){
		this.t=t;
	}
	
	public int compare(T o,Comparator<? super T> c){
		this.cpr=c;
		return cpr.compare(t, o);
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
		
		
		ComparableString cs=new ComparableString("a");
		Object co="b";
		System.out.println(cs.compareTo((Comparable<? extends String>) co));
		
		ComparatorObject<Double> cd=new ComparatorObject<>(10.3);
		System.out.println(cd.compare(10.1, new Comparator<Number>(){
			@Override
			public int compare(Number o1, Number o2) {
				return ((Double)o1).compareTo((Double) o2);
			}	
		}
		));
	}
}
