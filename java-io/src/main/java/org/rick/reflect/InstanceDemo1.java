package org.rick.reflect;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import model.Person;

public class InstanceDemo1 {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//使用Class的newInstance()方法创建对象，前提是那个类有无参构造函数
		Class c1=Class.forName("model.Person");
		Person p=(Person)c1.newInstance();
		System.out.println(p);
		
		//使用Constructor的newInstance()方法可以创建有参构造函数的对象
		Constructor[] cons=c1.getConstructors();
		Person p2=(Person)cons[2].newInstance("liyubin");
		System.out.println(p2);
		for(Constructor c:cons){
			System.out.println(c);
		}
	}
}
