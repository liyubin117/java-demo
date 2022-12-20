package org.rick.reflect;
interface China{	// 定义China接口
	public static final String NATIONAL = "China" ;	// 定义全局常量
	public static final String AUTHOR = "李宇彬" ;	// 定义全局常量
	public void sayChina() ;		// 无参的，没有返回值的方法
	public String sayHello(String name,int age) ;	// 定义有两个参数的方法，并返回内容
}
public class Person implements China{
	private String name ;
	private int age ;
	public Person(){	// 无参构造
	}
	public Person(String name){
		this.name = name ;	// 设置name属性
	}
	public Person(String name,int age){
		this(name) ;
		this.age = age ;
	}
	public void sayChina(){	// 覆写方法
		System.out.println("作者：" + AUTHOR + "，国籍：" + NATIONAL) ;
	}
	public String sayHello(String name,int age){
		return name + "，你好！我今年：" + age + "岁了！" ;
	}
	public void setName(String name){
		this.name = name ;
	}
	public void setAge(int age){
		this.age = age ;
	}
	public String getName(){
		return this.name ;
	}
	public int getAge(){
		return this.age ;
	}
};