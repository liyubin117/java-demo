package com.useful;

public class SystemDemo {

	public static void main(String[] args) {
		//取得当前系统的所有属性
		System.getProperties().list(System.out);
		
		System.out.println("当前字符集："+System.getProperty("file.encoding")
		+"\n当前目录："+System.getProperty("user.dir")
		);
		
		System.out.print("换行符："+System.getProperty("line.separator"));
	}

}
