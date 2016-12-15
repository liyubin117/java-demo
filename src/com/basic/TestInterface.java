package com.basic;

interface InterfaceA{
	//接口里的域变量默认是public static final，不用显式写
	int Y=100;
	//接口里的方法默认是public，不用显式写
	void a(int x);
	/*不能有实现体，即使是空实现也不行
	*void test(){}
	*/
}

interface InterfaceB{
	int Z=200;
	void b(int y);
}

interface InterfaceC extends InterfaceA,InterfaceB{
	int Z=300;
	void c(int y);
}

public class TestInterface implements InterfaceC{
	public void a(int x){
		System.out.println("implements the Interface of InterfaceC`s a method,x:"+x);
	}
	public void b(int x){
		System.out.println("implements the Interface of InterfaceC`s b method,x:"+x);
	}
	public void c(int x){
		System.out.println("implements the Interface of InterfaceC`s c method,x:"+x);
	}
	
	public static void main(String[] args){
		TestInterface ti=new TestInterface();
		if(ti instanceof InterfaceC ){
			ti.a(500);
			ti.b(400);
			ti.c(300);

			System.out.println("Y:"+ti.Y);
			System.out.println("Z:"+ti.Z);
		}
	}	
}
