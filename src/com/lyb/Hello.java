package com.lyb;

import java.util.Scanner;

public class Hello{
	
	static private int a=1;
	
	public static void main(String[] args){
		
		System.out.println("Hello World!");
		
		System.out.println("Hello"+" world!"=="Hello world!");
		
		String s1="Hello";
		String s2=" world!";
		System.out.println(s1+s2);
		System.out.println(s1+s2=="Hello world!");
		System.out.println((s1+s2).equals("Hello world!"));

		//final执行了宏替换，使s3、s4变成了宏变量
		final String s3="Hello";
		final String s4=" world!";
		System.out.println(s3+s4);
		System.out.println(s3+s4=="Hello world!");
		
		System.out.println("test");
		
		int a=10_9999_8888;
		System.out.println(a);
		
		char b='a';
		String c="aaa";
		switch(c){
		case("aaa"):{
			System.out.println("aaa");
			break;
		}
		case("aaaa"):{
			System.out.println("aaaa");
			break;
		}
		default:
			break;
		}
		
		Scanner scan=new Scanner(System.in);
		String st=scan.nextLine();
		System.out.println(st);

		
	}
	
	public int geta(){
		return Hello.a;
	}
}