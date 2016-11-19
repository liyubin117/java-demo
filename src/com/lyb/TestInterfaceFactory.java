package com.lyb;
//规范与实现分离，降低耦合度，可大大简化重构所需修改的工作量
//本例体现了抽象工厂的设计模式

//产品
interface Engine{
	
}
class EngineA implements Engine{
	public EngineA(){
		System.out.println("make EngineA");
	}
}
class EngineB implements Engine{
	public EngineB(){
		System.out.println("make EngineB");
	}
}

interface Aircondition{
	
}
class AirconditionA implements Aircondition{
	public AirconditionA(){
		System.out.println("make AirconditionA");
	}
}
class AirconditionB implements Aircondition{
	public AirconditionB(){
		System.out.println("make AirconditionB");
	}
}
//工厂
interface AbstractFactory{
	Engine createEngine();
	Aircondition createAircondition();
}
class FactoryBMW1 implements AbstractFactory{
	public FactoryBMW1(){
		createEngine();
		createAircondition();
		System.out.println("enjoy BMW1!");
	}
	@Override
	public Engine createEngine(){
		return new EngineA();
	}
	@Override
	public Aircondition createAircondition(){
		return new AirconditionA();
	}
}
class FactoryBMW2 implements AbstractFactory{
	public FactoryBMW2(){
		createEngine();
		createAircondition();
		System.out.println("enjoy BMW2!");
	}
	
	@Override
	public Engine createEngine(){
		return new EngineB();
	}
	@Override
	public Aircondition createAircondition(){
		return new AirconditionB();
	}
}

public class TestInterfaceFactory {
	public static void main(String[] args){
		FactoryBMW1 f1=new FactoryBMW1();
		FactoryBMW2 f2=new FactoryBMW2();
	}
}
