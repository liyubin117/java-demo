package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectFieldDemo1 {
	public static void main(String[] args) throws Exception{
		Class<?> c=Class.forName("com.reflect.Person");
		Object obj=null;
		//获取类变量
		obj=c.newInstance();
		Field f=c.getField("AUTHOR");
		System.out.println((String)f.get(obj));
		
		//获取实例变量
		Constructor[] cons=c.getConstructors();
		obj=cons[0].newInstance("李",25);
		f=c.getDeclaredField("name");
		//当获取私有属性时，需要设置可见性为true
		f.setAccessible(true);
		System.out.println((String)f.get(obj));
	}
}
