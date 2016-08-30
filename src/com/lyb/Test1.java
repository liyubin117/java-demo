package com.lyb;

public class Test1 {
	public static int k;
	public static Test1 t1=new Test1("t1");
	public static Test1 t2=new Test1("t2");
	public static int i=print("i");
	public static int n=99;
	public int j=print("j");
	{
		print("构造块");
	}
	static{
		print("静态构造块");
	}
	public Test1(String str){
		System.out.println((++k)+":"+"\ti="+i+"\tn="+n+"\tstr="+str);
		++i;
		++n;
	}
	public static int print(String str){
		System.out.println((++k)+":"+"\ti="+i+"\tn="+n+"\tstr="+str);
		++n;
		return ++i;
	}

	public static void main(String[] args) {
		Test1 t = new Test1("init");
	}

}
