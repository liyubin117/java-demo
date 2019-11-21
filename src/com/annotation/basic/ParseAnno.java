package com.annotation.basic;

import java.lang.reflect.Method;

public class ParseAnno {
	public static void main(String[] args){
		//解析类上的注解
		//使用类加载器
		try {
			Class<?> c=Class.forName("com.annotation.basic.Child");
			//判断类上是否存在某注解
			boolean isExist=c.isAnnotationPresent(Description.class);
			if(isExist){
				//拿到注解实例
				Description d=(Description) c.getAnnotation(Description.class);
				System.out.println("desc:"+d.desc()+",\tauthor:"+d.author()+",X\tage:"+d.age());
			}
			
			//解析方法上的注解
			Method[] ms=c.getMethods();
			for(Method m:ms){
				if(m.isAnnotationPresent(Description.class)){
					Description md=(Description)m.getAnnotation(Description.class);
					System.out.println("desc:"+md.desc()+",\tauthor:"+md.author()+",X\tage:"+md.age());
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
