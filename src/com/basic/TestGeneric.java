package com.basic;

class Pair<U,V>{
	U first;
	V second;

	public Pair(U u,V v){
		this.first=u;
		this.second=v;
	}
	
	public U getFirst(){
		return this.first;
	}

	public V getSecond(){
		return this.second;
	}
	
	public static <U,V>Pair<U,V> newPair(U u,V v){
		return (new Pair<U,V>(u,v));
	}
}

public class TestGeneric {
	public static void main(String[] args){
		Pair<Integer,String> pair=new Pair<Integer,String>(1,"liyubin");
		System.out.println(pair.getFirst()+" "+pair.getSecond());
			
		Pair<String,Integer> pair2=Pair.newPair("li", 2);
		System.out.println(pair2.getFirst()+" "+pair2.getSecond());

	}
}
