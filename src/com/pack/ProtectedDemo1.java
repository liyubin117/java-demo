package com.pack;

import com.pubtest.Employee;

//不同包的子类可以访问protected属性，不同包的非子类不可以

class Manager extends Employee{
	public static void getAddr(){
		System.out.println("addr:"+addr);
	}
}

class Manager2{
	public static void getAddr(){
		//找不到addr变量
		//System.out.println("addr:"+addr);
	}
}

public class ProtectedDemo1 {
	public static void main(String[] args){
		Manager.getAddr();
	}
}
