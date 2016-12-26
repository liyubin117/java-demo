package com.reflect;

import java.net.URL;

public class LoadDemo1 {

	public static void main(String[] args) throws ClassNotFoundException {
		//调用final类型的类变量，由于其值已经在编译阶段确定，是宏变量，不需要初始化
		System.out.println(Tester.FINALSTRING);
		
		System.out.println(Tester2.NOTFINALSTRING);
		
		//调用ClassLoader类对象的loadClass方法不会初始化类对象
		ClassLoader cl=ClassLoader.getSystemClassLoader();
		cl.loadClass("com.reflect.Tester2");
		
		//JVM启动时，初始类加载器层次结构：根类加载器、扩展类加载器、系统类加载器
		//根类加载器
		URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for(int i=0;i<urls.length;i++){
			System.out.println(urls[i]);
		}

	}

}

class Tester{
	static{
		System.out.println("a static block");
	}
	public static final String FINALSTRING="a final String"; 
}

class Tester2{
	static{
		System.out.println("a static block");
	}
	public static String NOTFINALSTRING="a not final String"; 
}
