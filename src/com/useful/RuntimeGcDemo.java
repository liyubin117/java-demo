package com.useful;

class Car{
	private String name;
	private int age;
	
	public Car(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	@Override
	public String toString(){
		return "name:"+this.name+" age:"+this.age;
	}
	
	public void finalize(){
		System.out.println("the car has been recycled:"+this);
	}
}

public class RuntimeGcDemo {
	public static void main(String[] args){
		Car c1=new Car("BMW X5",1);
		Runtime.getRuntime().gc();
		c1=null;
		Runtime.getRuntime().gc();

		Car c2=new Car("Audi A6",3);
		c2=null;
		System.gc();
	}
}

