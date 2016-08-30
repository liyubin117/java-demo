package com.lyb;
class CA{
	public static CA a=new CA(20);
	public static int j=8;
	public static CA b=new CA(30);
	public int i=10;
	static{
		j=5;
	}
	
	public CA(int e){
		System.out.println("CA构造器");
		System.out.println(i);
		System.out.println(j);
		System.out.println(e);


	}
}

public class TestStatic {
	public static void main(String[] args){
		CA b = new CA(40);

	}

}
