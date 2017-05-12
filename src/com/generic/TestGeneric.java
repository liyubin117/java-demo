package com.generic;



public class TestGeneric {
	public static void main(String[] args){
		//调用泛型类
		Pair<Object,Object> pair=new Pair<Object,Object>(1,"liyubin");
		System.out.println(pair.getFirst()+" "+pair.getSecond());
		//读取
		pair=pair.overCurrPair(new Pair<Integer,String>(2,"li"));
		System.out.println(pair.getFirst()+" "+pair.getSecond());
		//写入
		Pair<Integer,String> currPair=new Pair<>(3,"good");
		Pair<Object,Object> argPair=new Pair<Object,Object>(3,"yubin");
		currPair.overArgPair(argPair);
		System.out.println(argPair.getFirst()+" "+argPair.getSecond());

		
		//调用泛型函数，可自动判断类型
		Pair<String,Integer> pair2=Pair.newPair("li", 2);
		System.out.println(pair2.getFirst()+" "+pair2.getSecond());
		//也可显式指定类型
		Pair<String,Integer> pair3=Pair.<String,Integer>newPair("li", 2);
		System.out.println(pair3.getFirst()+" "+pair3.getSecond());

	}
}
