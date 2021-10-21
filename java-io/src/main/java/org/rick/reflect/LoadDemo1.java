package org.rick.reflect;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class LoadDemo1 {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		//调用final类型的类变量，由于其值已经在编译阶段确定，是宏变量，不需要初始化
		System.out.println(Tester.FINALSTRING);
		
		System.out.println(Tester2.NOTFINALSTRING);
		
		
		
		//JVM启动时，初始类加载器层次结构：根类加载器、扩展类加载器、系统类加载器
		//根类加载器
		System.out.println("根类加载器：");
		System.out.print("根类加载器的加载路径：");
		URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for(int i=0;i<urls.length;i++){
			System.out.println(urls[i]);
		}
		
		//扩展类加载器
		ClassLoader extLoader=ClassLoader.getSystemClassLoader().getParent();
		System.out.println("扩展类加载器："+extLoader);
		System.out.println("扩展类加载器的加载路径："+System.getProperty("java.ext.dirs"));
		System.out.println("扩展类加载器的父加载器："+extLoader.getParent());
		
		//系统类加载器。调用ClassLoader类对象的loadClass方法不会初始化类对象,加载路径通常由CLASSPATH变量指定，若无则默认以当前路径作为系统类加载器的加载路径
		ClassLoader cl=ClassLoader.getSystemClassLoader();
		System.out.println("系统类加载器："+cl);
		cl.loadClass("org.rick.reflect.Tester2");
		Enumeration<URL> eml=cl.getResources("");
		while(eml.hasMoreElements()){
			System.out.println("系统类加载器的加载路径："+eml.nextElement());
		}
		System.out.println("系统类加载器的加载路径："+System.getProperty("java.class.path"));

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
