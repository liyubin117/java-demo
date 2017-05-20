package com.memoryctl;
//初始化子类对象
//从最顶层初始化开始，依次往下逐层初始化。在同一层内，先初始化非表态初始化块，再根据子类构造器调用本类符合条件的构造器
//若子类构造器无super调用，则隐式调用父类无参构造器
public class ConstructorFather {
	public static void main(String[] args) {
		new Wolf(5.5);
	}
}

class Creature{
	{
		System.out.println("Creature的非静态初始化");
	}
	public Creature(){
		System.out.println("Creature的无参构造器");
	}
	public Creature(String name){
		this();
		System.out.println("Creature的带name参数的构造器，name是"+name);
	}
}
class Animal extends Creature{
	private int b=5;
	{
		System.out.println("Animal的非静态初始化");
		System.out.println(a);
		System.out.println(b);
	}
	//private int b=5;
	private static int a=10;

	public Animal(String name){
		super(name);
		System.out.println("Animal的带name参数的构造器，name是"+name);
	}
	public Animal(String name,int age){
		this(name);
		System.out.println("Animal的带name、age参数的构造器，age是"+age);
	}
}
class Wolf extends Animal{
	{
		System.out.println("Wolf的非静态初始化");
	}
	public Wolf(){
		super("灰太狼",3);
		System.out.println("Wolf的无参构造器");
	}
	public Wolf(double weight){
		this();
		System.out.println("Wolf的带weight的构造器，weight是"+weight);
	}
}
