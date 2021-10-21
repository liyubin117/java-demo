package org.rick.reflect;

class A{
	
}

public class GetClassDemo1 {

	public static void main(String[] args) {
		A a=new A();
		System.out.println(a.getClass());
		
		Class<?> c1=null;
		Class<?> c2=null;
		Class<?> c3=null;
		//Class.forName()实例化Class对象
		try{
			c1=Class.forName("org.rick.reflect.A");
		}catch(Exception e){
			e.printStackTrace();
		}
		//类.calss实例化Class对象
		c2=A.class;
		//类对象.getClass()实例化Class对象
		c3=new A().getClass();
	}

}
