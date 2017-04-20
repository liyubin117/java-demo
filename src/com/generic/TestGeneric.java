package com.generic;



public class TestGeneric {
	public static void main(String[] args){
		//调用泛型类
		Pair<Integer,String> pair=new Pair<Integer,String>(1,"liyubin");
		System.out.println(pair.getFirst()+" "+pair.getSecond());
		
		//调用泛型函数，可自动判断类型
		Pair<String,Integer> pair2=Pair.newPair("li", 2);
		System.out.println(pair2.getFirst()+" "+pair2.getSecond());
		//也可显式指定类型
		Pair<String,Integer> pair3=Pair.<String,Integer>newPair("li", 2);
		System.out.println(pair3.getFirst()+" "+pair3.getSecond());

	}
}
