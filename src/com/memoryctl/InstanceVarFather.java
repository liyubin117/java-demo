package com.memoryctl;

/**
 * 子类的方法可以访问父类的实例变量，这是因为子类继承父类就会获得父类的成员变量和方法；
 * 但父类的方法不能访问子类的实例变量，因为父类根本无从知道它将被哪个子类继承，它的子类将会增加怎样的成员变量。
 * 关键点：
 *1、构造器只是给已经创建好内存并赋默认值的变量赋新的值而已。
 *2、this在构造器中时，代表正在初始化的Java对象
 *3、一个对象内可以有两个同名变量（发生在父子类都有相同名字的变量时）
 *4、当变量的编译时类型和运行时类型不同时，通过该变量访问它引用的对象的实例变量时，该实例变量的值由声明该变量的类型决定。
 *	 但通过该变量调用它引用的对象的实例方法时，该方法行为将由它实际引用的对象决定
 */
public class InstanceVarFather {

	public static void main(String[] args) {
		Derived d=new Derived();
		//创建后的子类可以直接访问父类方法
		d.der();
	}

}

class Base{
	private int i=2;
	public Base(){
		System.out.println(this.i);
		this.display();
		//this实际的类型。编译时对象是Base，运行时对象是Derived
		System.out.println(this.getClass());
		this.sub();
		//编译时对象是Base，无der方法
		//this.der();
	}
	public void display(){
		System.out.println(i);
	}
	public void sub(){}
}
class Derived extends Base{
	private int i=22;
	public Derived(){
		i=222;
	}
	public void display(){
		System.out.println(i);
	}
	public void der(){}
}