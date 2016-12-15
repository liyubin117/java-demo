package com.designmodel;
//单例模式
class Singleton{
	private static Singleton instance;
	
	private Singleton(){
		System.out.println("make Singleton instance");
	}
	
	public static Singleton getInstance(){
		if(instance==null){
			instance=new Singleton();
		}
		return instance;
	}
}

public class TestSingleton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Singleton s1=Singleton.getInstance();
		Singleton s2=Singleton.getInstance();
		System.out.println(s1==s2);
	}

}
