package com.useful;

public class StringBufferOther {
	public static void main(String[] args){
		StringBuffer buf=new StringBuffer("ha");
		buf.insert(1, "el");
		System.out.println(buf);
		System.out.println(buf.length());
		
		buf=new StringBuffer("liyubin No.1");
		buf.replace(1, 1, "a");
		System.out.println(buf);
		buf.replace(1, 3, "aa");
		System.out.println(buf);

		buf=new StringBuffer("liyubin No.1");
		System.out.println(buf.reverse());
		
		buf=new StringBuffer("liyubin");
		String s=buf.substring(2);
		System.out.println(s);	
		s=buf.substring(0,2);
		System.out.println(s);	
		
		buf=new StringBuffer("hello");
		System.out.println(buf.delete(2, 4));
		System.out.println(buf.deleteCharAt(1));
		
		buf=new StringBuffer("hello");
		System.out.println(buf.indexOf("lo"));
		System.out.println(buf.indexOf("lo", 10));
		
		
		String s2=new String();
		for(int i=0;i<100;i++){
			s2+=i;
		}
		System.out.println(s2);
		//StringBuffer在处理频繁修改字符串时，性能高很多
		StringBuffer buf2=new StringBuffer();
		for(int i=0;i<100;i++){
			buf2.append(i);
		}
		System.out.println(buf2);

	}
	

}
