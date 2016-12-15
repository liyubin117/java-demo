package com.basic;
//字符串和数字相加时，数字型会隐式转换为字符串型被拼接。字符和数字相加时，字符先隐式转换为数字型（ascii码值），相加（因为整数的计算级别大于字符）。
public class TestChar {
	public static void main(String[] args){
		char a='1';
		String s="1";
		System.out.println("\'1\'+1="+(a+1));
		System.out.println("\"1\"+1="+(s+1));
		
		System.out.println("\'1\'的ascii码值："+Integer.valueOf(a));
	}
}
