package org.rick.useful;

public class StringBufferAppend2 {
	public static void main(String[] args){
		//StringBuffer对象是可变的
		StringBuffer buf=new StringBuffer("ha");
		fun(buf);
		System.out.println(buf);
		//String对象不可变
		String s=new String("string");
		fun2(s);
		System.out.println(s);
		
	}
	
	public static void fun(StringBuffer buf){
		buf.append(" hello").append(" liyubin");
	}
	
	public static void fun2(String s){
		s=s+" hello"+" liyubin";
	}
}
