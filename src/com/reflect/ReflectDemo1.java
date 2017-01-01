package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectDemo1 {

	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> c=Class.forName("com.reflect.Person");
		//获得类实现的所有接口
		Class<?>[] interfaces=c.getInterfaces();
		for(Class<?> i:interfaces){
			System.out.println("实现的接口："+i.getName());
		}
		//获得父类
		Class<?> fc=c.getSuperclass();
		System.out.println("父类："+fc.getName());
		//取得构造方法
		Constructor[] cs=c.getConstructors();
		for(Constructor con:cs){
			System.out.print("构造方法名："+con.getName()+" 修饰符："+Modifier.toString(con.getModifiers())+" 参数类型：");
			for(Class<?> cp:con.getParameterTypes()){
				System.out.print(cp.getName()+" ");
			}
			System.out.println();
		}
		//取得本类声明的所有方法
		Method[] ms=c.getDeclaredMethods();
		for(Method m:ms){
			System.out.print("方法名："+m.getName()+" 修饰符："+Modifier.toString(m.getModifiers())+" 返回类型："+m.getReturnType().getName()+" 参数类型：");
			for(Class<?> cfun:m.getParameterTypes()){
				System.out.print(cfun.getName()+" ");
			}
			System.out.print(" "+"异常：");
			for(Class<?> cfun:m.getExceptionTypes()){
				System.out.print(cfun.getName()+" ");
			}
			System.out.println();
		}
		System.out.println("------------------");
		//取得本类涉及的所有方法
		ms=c.getMethods();
		for(Method m:ms){
			System.out.print("方法名："+m.getName()+" 修饰符："+Modifier.toString(m.getModifiers())+" 返回类型："+m.getReturnType().getName()+" 参数类型：");
			for(Class<?> cfun:m.getParameterTypes()){
				System.out.print(cfun.getName()+" ");
			}
			System.out.print(" "+"异常：");
			for(Class<?> cfun:m.getExceptionTypes()){
				System.out.print(cfun.getName()+" ");
			}
			System.out.println();
		}
		System.out.println("------------------");
		//取得本类声明的所有属性名
		Field[] fs=c.getDeclaredFields();
		for(Field f:fs){
			System.out.print("属性名："+f.getName()+" 修饰符："+Modifier.toString(f.getModifiers())+" 参数类型："+f.getType().getName());
			System.out.println();
		}
		
	}

}
