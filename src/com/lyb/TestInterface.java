package com.lyb;

interface Output{
	//接口里的域变量默认是public static final，不用显式写
	int Y=100;
	//接口里的方法默认是public，不用显式写
	void output(int x);
	/*不能有实现体，即使是空实现也不行
	*void test(){}
	*/
}

public class TestInterface implements Output{
	public void output(int x){
		System.out.println("implements the Interface of Output`s output method,x:"+x);
	}
	
	public static void main(String[] args){
		TestInterface ti=new TestInterface();
		ti.output(500);
		System.out.println(ti.Y);
	}
	
	
}
