package com.useful;

public class StringBufferAppend1 {
	public static void main(String[] args){
		StringBuffer buf=new StringBuffer("ha");
		buf.append(" hello");
		buf.append(" liyubin");
		buf.append("\n字符:").append('a');
		buf.append("\n数字:").append(1);
		buf.append("\n字符串:").append("hahaha");
		System.out.println(buf);
	}
}
