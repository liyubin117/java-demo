package org.rick.memoryctl;

/**
 * 在访问权限允许的情况下，子类可以调用父类方法，这是因为子类继承父类会获得父类定义的成员变量和方法；
 * 但父类不能调用子类的方法，因为父类根本无从知道它将被哪个子类继承，它的子类将会增加怎样的方法。
 *
 */
public class FunctionFather {

	public static void main(String[] args) {
		System.out.println(new GoodMonkey("good money!",5.1));
	}

}

class Monkey{
	private String desc;
	
	public Monkey(){
		this.desc=getDesc();
	}

	public String getDesc() {
		return "Monkey";
	}

	@Override
	public String toString() {
		return desc;
	}
}

class GoodMonkey extends Monkey{
	private String name;
	private double weight;
	public GoodMonkey(String name,double weight){
		this.name=name;
		this.weight=weight;
	}
	@Override
	public String getDesc(){
		return "GoodMoney [name="+name+" weight="+weight+"]";
	}
	
}