package com.generic;
//泛型类
public class Pair<U,V>{
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
	//泛型函数
	public static <U,V> Pair<U,V> newPair(U u,V v){
		return (new Pair<U,V>(u,v));
	}
	//通配符?
	//? extends 用于读取
	public Pair<U,V> overCurrPair(Pair<? extends U,? extends V> pair){
		return new Pair<U,V>(pair.getFirst(),pair.getSecond());
	}
	//? super 用于写入
	public void overArgPair(Pair<? super U,? super V> pair){
		pair.first=first;
		pair.second=second;
	}
}